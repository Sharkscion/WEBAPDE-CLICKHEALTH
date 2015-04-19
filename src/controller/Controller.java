package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import model.database.*;

public class Controller {

    private AppointmentDAO ad;
    private DoctorDAO dd;
    private DoctorScheduleDAO sd;
    private HospitalDAO hd;
    private PatientDAO pd;
    private ContactDAO cd;
    private UserDAO ud;
    private NotificationDAO nd;
//	private static Controller controller;
//
//	public static synchronized Controller getInstance() 
//	{
//        if (controller == null) {
//            controller = new Controller();
//        }
// 
//        return controller;
//    }

    public Controller() {
        ad = new AppointmentDAO();
        dd = new DoctorDAO();
        sd = new DoctorScheduleDAO();
        hd = new HospitalDAO();
        pd = new PatientDAO();
        ud = new UserDAO();
        cd = new ContactDAO();
        nd = new NotificationDAO();
    }

    public boolean changeAppointmentStat(int id, String stat) {
        return ad.changeAppointmentStat(id, stat);
    }

    public boolean changeNotificationStat(Notification n) {
        return nd.updateData(n);
    }

    public boolean addNotification(Notification notif) {
        return nd.insertData(notif);
    }

    public boolean addAppointment(Appointment app) {
        return ad.insertData(app);
    }

    public boolean addContact(UserContact uc) {
        return cd.insertData(uc);
    }

    public boolean addDoctor(Doctor d) {
        return dd.insertData(d);
    }

    public boolean addDoctorSchedule(DoctorSchedule ds) {
        return sd.insertData(ds);
    }

    public boolean addUser(User u) {
        return ud.insertData(u);
    }
    
    public void deleteSchedules(int licenseid){
        sd.deleteAll(licenseid);
    }

    public boolean addPatient(Patient p) {
        return pd.insertData(p);
    }

    public void editPatient(Patient p) {

        pd.updateData(p);
    }

    public void editDoctor(Doctor d) {
        dd.updateData(d);
    }

    public void editUser(User u) {
        ud.updateData(u);
    }

    public User getAppointmentDoctorInfo(int appointment) {
        return ud.getAppointmentDoctorUserInfo(appointment);
    }

    public Appointment getAppointment(int appId) {
        return ad.getData(appId);
    }

    public Hospital getHospital(String hospitalName) {
        return hd.getData(hospitalName);
    }

    public Hospital getHospitalByID(int hospID) {
        return hd.getHospitalByID(hospID);
    }

    public User getUser(String username) {
        User u = ud.getDataUsername(username);
        return u;
    }

    public Patient getPatientByID(int patientID) {
        return pd.getPatientByID(patientID);
    }

    public Doctor getDoctor(String username) {
        return dd.getData(username);
    }

    public Notification getNotification(int notifID) {
        return nd.getData(notifID);
    }

    public Doctor getDoctorByUserId(int userID) {
        return dd.getDoctorByUserId(userID);
    }

    public User validateUser(String username, String password) {
        return ud.validateUser(username, password);
    }

    public Iterator<Hospital> getAllHospitals() {
        return hd.getAllData();
    }

    public Iterator getAllDoctors() {
        return dd.getAllData();
    }

    public Iterator<DoctorSchedule> getAllDoctorSchedule() {
        return sd.getAllData();
    }

    public Iterator<String> getAllSpecializations() {
        return dd.getAllSpecializations();
    }

    public Iterator<Notification> getAllNotificationNotViewed(int patientID) {
        return nd.getAllNotificationNotViewed(patientID);
    }

    public Iterator<User> getAllUsers() {
        return ud.getAllData();
    }

    public Iterator<String> getSpecializations(String specialization) {
        return dd.getSpecializations(specialization);
    }

    public User getUserInstance(String userID) {
        return ud.getData(userID);
    }

    public Patient getPatientInstance(String username) {
        return pd.getData(username);
    }

    public DoctorSchedule getDoctorSchedule(int scheduleID) {
        return sd.getData(scheduleID);
    }

    public Iterator<Appointment> getPatientAppointments(int patientID) {
        return ad.getPatientAppointments(patientID);
    }

    public Iterator<Appointment> getDoctorAppointments(int licenseID) {
        return ad.getDoctorAppointments(licenseID);
    }

    public Iterator<Appointment> getRequestAppointment(int licenseID) {
        return ad.getRequestAppointments(licenseID);
    }

    public Iterator<Hospital> getSpecializationHospitals(String specialization) {
        return hd.getSpecializationHospitals(specialization);
    }

    public Iterator<Doctor> getSpecializationHospitalDoctors(String specialization, int hospitalID) {
        return dd.getSpecializationHospitalDoctors(specialization, hospitalID);
    }

    public Iterator<DoctorSchedule> getSpecializationHospitalDoctorScheds(String specialization, int hospitalID) {

        return sd.getSpecializationHospitalDoctorScheds(specialization, hospitalID);
    }

    public Iterator<UserContact> getUserContacts(int id) {
        return cd.getUserContacts(id);
    }

    public Iterator<DoctorSchedule> getDoctorsSchedules(int licenseID, int hospitalID) {
        return sd.getDoctorsSchedules(licenseID, hospitalID);
    }
    
    public Iterator<DoctorSchedule> getSchedules(int licenseid){
        return sd.getSchedules(licenseid);
    }

    public boolean resolvePatientAppointment(int appId) {
        return ad.resolvePatientAppointment(appId);
    }

    public boolean resolveDoctorAppointment(int appId) {
        return ad.resolveDoctorAppointment(appId);
    }

    public int getNotifCount(int patientID) {
        return nd.getNotificationCount(patientID);
    }

    public boolean checkDay(String day, int doctor) {
        return sd.checkDay(day, doctor);
    }

    public DoctorSchedule getDoctorScheduleByDay(String day, int doctor) {
        return sd.getDoctorScheduleByDay(day, doctor);
    }

    public boolean checkDaySched(DoctorSchedule ds, String time, String date) {

        return ad.checkDaySched(ds, time, date);
    }
}
