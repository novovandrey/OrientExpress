package ru.novand.orientexpress.utils;

import java.util.HashMap;

public class Routes {

    private static HashMap<String, String> routes;

    public static final String home = "/home";
    public static final String login = "/login";
    public static final String logout = "/logout";
    public static final String buyTicket = "/buyTicket";
    public static final String payTicket = "/payTicket";
    public static final String getmarkers = "/getmarkers";
    public static final String downloadPDF = "/downloadPDF";
    public static final String passengerList = "/passengerList";
    public static final String getAllPassengers = "/getAllPassengers";
    public static final String schedule = "/schedule";
    public static final String findSchedule = "/findSchedule";
    public static final String scheduleDetail = "/scheduleDetail";
    public static final String stationschedule = "/stationschedule";
    public static final String stationscheduleData = "/stationscheduleData";
    public static final String addstation = "/addstation";
    public static final String addtrain = "/addtrain";
    public static final String trains = "/trains";
    public static final String addTrainSchedule = "/addTrainSchedule";
    public static final String trainRouteList = "/trainRouteList";
    public static final String initTrainroute = "/initTrainroute";
    public static final String trainrouteAdd = "/trainroute/add";



    private static void setRoutes()
    {
        if(routes == null)
        {
            routes = new HashMap<String, String>();

            routes.put("home", home);
            routes.put("login", login);
            routes.put("logout", logout);
            routes.put("buyTicket", buyTicket);
            routes.put("payTicket", payTicket);
            routes.put("getmarkers", getmarkers);
            routes.put("downloadPDF", downloadPDF);
            routes.put("passengerList", passengerList);
            routes.put("getAllPassengers", getAllPassengers);
            routes.put("schedule", schedule);
            routes.put("findSchedule", findSchedule);
            routes.put("scheduleDetail", scheduleDetail);
            routes.put("stationschedule", stationschedule);
            routes.put("stationscheduleData", stationscheduleData);
            routes.put("addstation", addstation);
            routes.put("addtrain", addtrain);
            routes.put("trains", trains);
            routes.put("addTrainSchedule", addTrainSchedule);
            routes.put("trainRouteList", trainRouteList);
            routes.put("initTrainroute", initTrainroute);
            routes.put("trainrouteAdd", trainrouteAdd);
        }
    }

    public static HashMap<String, String> getRoutes()
    {
        setRoutes();

        return routes;
    }

    public static String getRoute(String destin)
    {
        setRoutes();

        return routes.get(destin);
    }

}