import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SharedModule } from '../../../../shared/shared.module';

@Component({
  selector: 'app-view-task-details',
  imports: [SharedModule],
  templateUrl: './view-task-details.component.html',
  styleUrl: './view-task-details.component.scss'
})
export class ViewTaskDetailsComponent {

  id!:number;
  commentForm!: FormGroup;
  taskData: any;
  comments:any;

  constructor(private service: AdminService,
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.commentForm = this.fb.group({
      content: [null, [Validators.required]]
    }) 
    this.getTaskById();
  };

  getTaskById() {
    this.service.getTaskById(this.id).subscribe(
      (res) => {
      console.log(res);
      this.taskData = res;
      this.getCommentsByTaskId()
    })
  };

  publishComment() {
    this.service.createComment(this.id, this.commentForm.get('content')?.value).subscribe((res) => {
      console.log(res);
      if (res.id != null) {
        this.snackbar.open('Вы успешно опубликовали комментарий!', 'Close', {duration: 5000});
        this.getCommentsByTaskId();
      } else {
        this.snackbar.open('Что-то пошло не так!', 'Error', {duration: 5000});
      }
    })
  };

  getCommentsByTaskId() {
    this.service.getCommentsById(this.id).subscribe(
      (res) => {
      console.log(res);
      this.comments = res;
    })
  };

}
