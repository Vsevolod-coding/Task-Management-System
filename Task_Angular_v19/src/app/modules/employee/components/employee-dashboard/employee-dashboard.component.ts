import { Component } from '@angular/core';
import { SharedModule } from '../../../../shared/shared.module';
import { EmployeeService } from '../../services/employee.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-employee-dashboard',
  imports: [SharedModule],
  templateUrl: './employee-dashboard.component.html',
  styleUrl: './employee-dashboard.component.scss'
})
export class EmployeeDashboardComponent {

  listOfTasks: any = [];

  constructor(private service: EmployeeService,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getTasks();
  };

  getTasks() {
    this.service.getTasksByUserId().subscribe((response) => {
      console.log(response);
      this.listOfTasks = response;
    })
  }

  updateStatus(id: number, status: string) {
    this.service.updateTask(id, status).subscribe((res) => {
      if(res.id != null) {
        this.snackbar.open('Задача успешно обновилась!', 'Закрыть', {duration: 5000});
        this.getTasks();
      } else {
        this.snackbar.open('Что-то пошло не так!', 'Закрыть', {duration: 5000});
      }
    })
  }

}
