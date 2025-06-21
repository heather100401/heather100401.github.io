# Node of a linked list
class Node:
    def __init__(self, id, data):
        # reference to next node
        self.next = None
        # reference to previous node
        self.prev = None
        self.id = id
        self.data = data
