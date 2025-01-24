import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { SharedModule } from '../../../../shared/shared.module';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-admin-dashboard',
  imports: [SharedModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {
  listOfTasks:any = [];
  searchTaskForm!:FormGroup;
  
  constructor(private service: AdminService,
    private fb:FormBuilder,
    private snackbar:MatSnackBar
  ) {

  }

  ngOnInit() {
    this.searchTaskForm = this.fb.group({
      title:[null]
    })
    this.getTasks();
  };

  getTasks() {
    this.service.getTasks().subscribe((response)=> {
      console.log(response);
      this.listOfTasks = response;
    })
  }

  submitForm() {
    console.log(this.searchTaskForm.value);
    const title = this.searchTaskForm.get('title')!.value;
    this.listOfTasks = [];
    if (title.length === 0) {
      this.getTasks();
    }
    this.service.searchTasks(title).subscribe((response)=> {
      this.listOfTasks = response;
    })
  }

  deleteTask(id:number) {
    this.service.deleteTask(id).subscribe((response)=>{
      this.getTasks;
      window.location.reload();
      this.snackbar.open('Вы удалили задачу!', 'Закрыть', {duration:5000});
    })
  }
}
