from Linked_List.node import Node

class Linked_List:
    def __init__(self):
        self.head = None

    # Insert data after head
    def insert(self, id, data):
        node = Node(id, data)
        if self.head is None:           # Set node as head if first node
            self.head = node
            return
                                        # Else, insert after head
        last = self.head
        while last.next:
            last = last.next
        last.next = node

    # Deletes last node
    def remove(self, id):
        temp = self.head                # Traverse list until end is found
        prev = None
        while temp:
            if temp.id == id:
                if prev is None:
                    self.head = temp.next
                else:
                    prev.next = temp.next
                return True
            prev = temp
            temp = temp.next
        return False

    # Search for node based on data
    def search(self, id):    
        current = self.head             
        while current:
            if current.id == id:   
                return current.data
            current = current.next
        return False
    
    def next_node(self, current):
        if current.next:
            current = current.next
            return current
        return None
    
    def get_head(self):
        return self.head
    
    # Prints list (For Testing)
    def print(self):
        temp = self.head
        while temp:
            print(temp.data, end=" -> ")
            temp = temp.next
        print("None")
