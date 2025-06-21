from Appointment.appointment import Appointment
from Appointment.appointmentService import AppointmentService
from Contact.contact import Contact
from Contact.contactService import ContactService
from Task.task import Task
from Task.taskService import TaskService
from datetime import datetime

def main():

    appointment_service = AppointmentService()
    contact_service = ContactService()
    task_service = TaskService()

    def get_task(id):
        task = task_service.find_task_by_id(id)
        return task
    
    def get_appointment(id):
        appointment = appointment_service.find_appointment_by_id(id)
        return appointment
    
    def get_contact(id):
        contact = contact_service.find_contact_by_id(id)
        return contact



    while True:
        print("Hello!")
        print("\nMain Menu:")
        print("1. Appointments")
        print("2. Contact Information")
        print("3. Tasks")
        print("4. Exit")
        choice = input("Choose an option: ")

        if choice == '1':
            while True:
                print("\nAppointments:")
                print("1. Make a New Appointment")
                print("2. Cancel Appointment")
                print("3. View Upcoming Appointments")
                print("4. Back to Main Menu")
                appt_choice = input("Choose an option: ")

                if appt_choice == '1':
                    try:
                        appointment_id = input("Appointment ID: ")
                        date_str = input("Date (MM/DD/YYYY): ")
                        date = datetime.strptime(date_str, "%m/%d/%Y")
                        description = input("Description: ")
                        appointment = Appointment(appointment_id, date, description)
                        appointment_service.add_appointment(appointment)
                        print("Appointment added.\n")
                    except Exception as e:
                        print(f"Error: {e}")

                elif appt_choice == '2':
                    appointment_id = input("Enter Appointment ID to delete: ")
                    try:
                        appointment = get_appointment(appointment_id)
                        appointment_service.delete_appointment(appointment)
                        print("Appointment deleted.")
                    except Exception as e:
                        print(f"Error: {e}")

                elif appt_choice == '3':
                    appointment_service.print_list()

                elif appt_choice == '4':
                    break

        elif choice == '2':
            while True:
                print("\nContact Information:")
                print("1. Add New Contact")
                print("2. Delete Contact")
                print("3. Update Contact Information")
                print("4. View Contact Information")
                print("5. Back to Main Menu")
                contact_choice = input("Choose an option: ")

                if contact_choice == '1':
                    try:
                        contact_id = input("Contact ID: ")
                        first_name = input("First Name: ")
                        last_name = input("Last Name: ")
                        phone = input("Phone (10 digits): ")
                        address = input("Address: ")

                        contact = Contact(contact_id, first_name, last_name, phone, address)
                        contact_service.add_contact(contact)
                        print("Contact added.")
                    except Exception as e:
                        print(f"Error: {e}")

                elif contact_choice == '2':
                    contact_id = input("Enter contact ID to delete: ")
                    try:
                        contact = get_contact(contact_id)
                        contact_service.delete_contact(contact)
                        print("Contact deleted.\n")
                    except Exception as e:
                        print(f"Error: {e}")

                elif contact_choice == '3':
                    contact_id = input("Enter contact ID to update: ")
                    contact = get_contact(contact_id)
                    contact_service.print_contact(contact)
                    update_field = input("Update:\n1. First Name\n2. Last Name\n3. Phone\n4. Address\n")
                    new_val = input("Enter new value: ")

                    try:
                        if update_field == '1':
                            contact_service.update_first_name(contact, new_val)
                        elif update_field == '2':
                            contact_service.update_last_name(contact, new_val)
                        elif update_field == '3':
                            contact_service.update_phone(contact, new_val)
                        elif update_field == '4':
                            contact_service.update_address(contact, new_val)
                        print("Contact updated.")
                    except Exception as e:
                        print(f"Error: {e}")

                elif contact_choice == '4':
                    contact_service.print_list()

                elif contact_choice == '5':
                    break

        elif choice == '3':
            while True:
                print("\nTasks:")
                print("1. Add New Task")
                print("2. Delete Task")
                print("3. Update Task")
                print("4. View Tasks")
                print("5. Back to Main Menu")
                task_choice = input("Choose an option: ")

                if task_choice == '1':
                    try:
                        task_id = input("Task ID: ")
                        name = input("Task Name: ")
                        desc = input("Task Description: ")
                        task = Task(task_id, name, desc)
                        task_service.add_task(task)
                        print("Task added.")
                    except Exception as e:
                        print(f"Error: {e}")
                
                elif task_choice == '2':
                    task_id = input("Enter task ID to delete: ")
                    try:
                        task = get_task(task_id)
                        task_service.delete_task(task)
                        print("Task deleted.")
                    except Exception as e:
                        print(f"Error: {e}")

                elif task_choice == '3':
                    task_id = input("Enter task ID to update: ")
                    task = get_task(task_id)
                    task_service.print_task(task)
                    update_field = input("Update:\n1. Name\n2. Description\nChoose: ")
                    new_val = input("Enter new value: ")

                    try:         
                        if update_field == '1':
                            task_service.update_name(task, new_val)
                        elif update_field == '2':
                            task_service.update_description(task, new_val)
                        print("Task updated.")
                    except Exception as e:
                        print(f"Error: {e}")

                elif task_choice == '4':
                    task_service.print_list()

                elif task_choice == '5':
                    break

        elif choice == '4':
            print("Goodbye!")
            break

        else:
            print("Invalid input. Please choose a valid option.")

main()
exit()

