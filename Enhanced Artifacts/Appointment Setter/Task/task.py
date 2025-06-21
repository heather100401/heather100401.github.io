class Task:
    def __init__(self, task_id, name, description):
        self._task_id = task_id                 # Unique, cannot be updated, max length 10
        self._name = name                       # Cannot be null, max length 20
        self._description = description         # Cannot be null, max length 50

    #getters
    def get_task_id(self):
        return self._task_id
    
    def get_name(self):
        return self._name
    
    def get_description(self):
        return self._description


    #setters
    def set_name(self, name):
        self._name = name

    def set_description(self, description):
        self._description = description   
