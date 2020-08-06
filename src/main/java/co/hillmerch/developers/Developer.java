package co.hillmerch.developers;

public class Developer {

	private Long id;
	private String fullName;

	private int taskInProgress;
	private int taskToDo;
	private int taskDone;


	Developer(){
	}

	private Developer(Long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getTaskInProgress() {
		return taskInProgress;
	}

	public void setTaskInProgress(int taskInProgress) {
		this.taskInProgress = taskInProgress;
	}

	public int getTaskToDo() {
		return taskToDo;
	}

	public void setTaskToDo(int taskToDo) {
		this.taskToDo = taskToDo;
	}

	public int getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(int taskDone) {
		this.taskDone = taskDone;
	}

	public static Developer of(Long id, String fullName) {
		return new Developer(id, fullName);
	}

	@Override
	public String toString() {
		return "Developer{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", taskToDo=" + taskToDo +
				", taskInProgress=" + taskInProgress +
				", taskDone=" + taskDone +
				'}';
	}
}
