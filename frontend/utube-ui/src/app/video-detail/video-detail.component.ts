import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../video.service";
import {subscribeOn} from "rxjs";
import {UserService} from "../user.service";

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent {
  videoId: string;
  videoUrl!: string;
  videoTitle!: string;
  videoDescription!: string;
  tags: Array<string> = [];
  videoAvailable: boolean = false;
  likeCount: number = 0;
  dislikeCount: number = 0;
  viewCount: number = 0;
  showSubscribeButton: boolean = true;
  showUnSubscribeButton: boolean = false;
  constructor(private activateRoute: ActivatedRoute,
              private videoService: VideoService,
              private userService: UserService) {
    ({videoId: this.videoId} = this.activateRoute.snapshot.params);
    this.videoService.getVideo(this.videoId)
      .subscribe(data => {
        this.videoUrl = data.videoUrl;
        this.videoTitle = data.title;
        this.videoDescription = data.description;
        this.tags = data.tags;
        this.videoAvailable = true;
        this.likeCount = data.likeCount;
        this.dislikeCount = data.dislikeCount;
        this.viewCount = data.viewCount;
      })
  }

  likeVideo() {
    this.videoService.likeVideo(this.videoId).subscribe(data => {
      this.likeCount = data.likeCount;
      this.dislikeCount = data.dislikeCount;
    });
  }

  disLikeVideo() {
    this.videoService.disLikeVideo(this.videoId).subscribe(data => {
      this.likeCount = data.likeCount;
      this.dislikeCount = data.dislikeCount;
    });
  }

  subscribeToUser() {
    let userId = this.userService.getUserId();
    this.userService.subscribeToUser(userId).subscribe(data => {
      this.showUnSubscribeButton = true;
      this.showSubscribeButton = false;
    });
  }

  unSubscribeToUser() {
    let userId = this.userService.getUserId();
    this.userService.unSubscribeUser(userId).subscribe(data => {
      this.showSubscribeButton = true;
      this.showUnSubscribeButton = false;
    });
  }
}
