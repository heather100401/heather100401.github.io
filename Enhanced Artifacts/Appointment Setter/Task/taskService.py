from Task.task import Task
from Linked_List.linked_list import Linked_List

class TaskService:
    def __init__(self):
        self.tasks = Linked_List()

    # Add a new task
    def add_task(self, task):
        if self._search(task) != False:
            raise ValueError("Task ID already exists")
        id = task.get_task_id()
        name = task.get_name()
        description = task.get_description()
        if id == None:
            raise ValueError("ID cannot be null")
        if len(id) > 10:
            raise ValueError("ID cannot be more than 10 characters")
        if name == None:
            raise ValueError("Name cannot be null")
        if len(name) > 20:
            raise ValueError("Name cannot be more than 20 characters")
        if description == None:
            raise ValueError("Description cannot be null")
        if len(description) > 50:
            raise ValueError("Description cannot be more than 50 characters")
        self.tasks.insert(id, task)

    # Delete a task
    def delete_task(self, task):
        if self._search(task) == False:
            raise ValueError("Task ID not found")
        self.tasks.remove(task.get_task_id())
        return True


    # Update values
    def update_name(self, task, name):
        if self._search(task) == False:
            raise ValueError("Task ID not found")
        if name == None:
            raise ValueError("Name cannot be null")
        if len(name) > 20:
            raise ValueError("Name cannot be more than 20 characters")
        id = task.get_task_id()
        description = task.get_description()
        new_task = Task(id, name, description)
        self.delete_task(task)
        self.add_task(new_task)
    
    def update_description(self, task, description):
        if self._search(task) == False:
            raise ValueError("Task ID not found")
        if description == None:
            raise ValueError("Description cannot be null")
        if len(description) > 50:
            raise ValueError("Description cannot be more than 50 characters")
        id = task.get_task_id()
        name = task.get_name()
        new_task = Task(id, name, description)
        self.delete_task(task)
        self.add_task(new_task)
    

    # Internal search function
    def _search(self, task):
        task = self.tasks.search(task.get_task_id())
        return task
    
    # External, returns task
    def find_task_by_id(self, id):
        task = self.tasks.search(id)
        if task == False:
            raise ValueError("Task ID not found")
        return task
    
    
    # Prints linked list
    def print_list(self):
        node = self.tasks.get_head()
        while node:
            task = node.data
            self.print_task(task)
            node = self.tasks.next_node(node)

    # Prints a singular task
    def print_task(self, task):
        print("\nTask", task.get_task_id(), "\n\tName:\t\t", task.get_name(), "\n\tDescription:\t", task.get_description())


