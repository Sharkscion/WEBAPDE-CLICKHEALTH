package servlets;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.servlet.http.Cookie;

import model.Appointment;
import model.Doctor;
import model.DoctorSchedule;
import model.Hospital;
import model.Patient;
import model.User;

/**
 * Servlet implementation class ContactDocServlet
 */
@WebServlet("/EditPatientServlet")
public class EditPatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        Controller con = new Controller();
        Patient p;
        String userID = "";
        User u;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userID = cookie.getValue();
            }
        }

        u = con.getUserInstance(userID);

        if (u.getType().equals("patient")) {
            u = con.getPatientInstance(u.getUsername());
        } else {
            u = con.getDoctor(u.getUsername());
        }

        String type = request.getParameter("settingCategory");
        System.out.println("WENT HERE Type: " + type);
        if (type.equals("name")) {
            String first = request.getParameter("firstNameTxt");
            String last = request.getParameter("lastNameTxt");

            if (!first.equals("")) {
                u.setFirstname(first);
            }
            if (!last.equals("")) {
                u.setLastname(last);
            }
            con.editUser(u);

        } else if (type.equals("address")) {
            ((Patient) u).setStreet(request.getParameter("streetTxt"));
            ((Patient) u).setCity(request.getParameter("cityTxt"));
            con.editPatient(((Patient) u));

        } else if (type.equals("username")) {
            u.setUsername(request.getParameter("usernameTxt"));
            con.editUser(u);

        } else if (type.equals("password")) {
            if (u.getPassword().equals(encryptPassword(request.getParameter("currentpasswordTxt")))) {
                if (request.getParameter("newpasswordTxt").equals(request.getParameter("confirmpasswordTxt"))) {
                    u.setPassword(encryptPassword(request.getParameter("confirmpasswordTxt")));
                    con.editUser(u);
                    if (u.getType().equals("patient")) {
                        response.sendRedirect("user-account-settings.jsp");
                    } else {
                        response.sendRedirect("doctor-account-settings.jsp");
                    }
                }
            }
        } else if (type.equals("specialization")) {
            ((Doctor) u).setSpecialization(request.getParameter("specializationTxt"));
            con.editDoctor((Doctor) u);
        } else if (type.equals("schedule")) {
            int i = 1;
            con.deleteSchedules(((Doctor) u).getLicenseID());
            Hospital hosp = null;
            DateFormat sdf = new SimpleDateFormat("hh:mm");
            DoctorSchedule ds = null;
            String day, start, end, hospital;
            String checker = request.getParameter("day" + i);
            System.out.println("CHECKER: " + checker);
            while (checker != null) {
                day = request.getParameter("day" + i);
                start = request.getParameter("startTime" + i);
                end = request.getParameter("endTime" + i);
                hospital = request.getParameter("hospital" + i);
                

                if (!day.equals("") && !start.equals("") && !end.equals("") && !hospital.equals("")) {
                    hosp = con.getHospital(hospital);
                    try {
                        Date dateStart = sdf.parse(start);
                        Time startTime = new Time(dateStart.getTime());

                        Date dateEnd = sdf.parse(end);
                        Time endTime = new Time(dateEnd.getTime());

                        ds = new DoctorSchedule(0, day, startTime, endTime, ((Doctor) u).getLicenseID(), hosp.getHospID());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    con.addDoctorSchedule(ds);
                }
                i++;
                checker = request.getParameter("day" + i);
            }
        }

        if (!type.equals("password")) {
            if (u.getType().equals("patient")) {
                response.sendRedirect("user-account-settings.jsp");
            } else {
                response.sendRedirect("doctor-account-settings.jsp");
            }
        }
    }

    public static String encryptPassword(String password) {
        String sha1 = "";
        System.out.println("PASSWORD: " + password);
        if (password.equals("") == false) {
            System.out.println("PASSORD");
            try {
                MessageDigest crypt = MessageDigest.getInstance("SHA-1");
                crypt.reset();
                crypt.update(password.getBytes("UTF-8"));
                sha1 = byteToHex(crypt.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
