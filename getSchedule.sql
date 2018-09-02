SELECT tr.TRAINCODE, date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE ),s1.INTERVAL_M MINUTE,
    st1.STATIONNAME as st1,date_add(d1.DEPARTUREDATE,INTERVAL s2.INTERVAL_M MINUTE ),s2.INTERVAL_M MINUTE,
    st2.STATIONNAME  as st2
FROM Schedule s1
  join Schedule s2 on s1.TRAIN_ID = s2.TRAIN_ID
  join TrainScheduleDates d1 on s1.TRAIN_ID = d1.TRAIN_ID
  join Station st1 on st1.STATION_ID = s1.DEPARTURE_STATION_ID
  join Station st2 on st2.STATION_ID = s2.DEPARTURE_STATION_ID
  join Train tr on tr.TRAIN_ID = s1.TRAIN_ID
where st1.STATIONNAME = 'Moscow'
      and st2.STATIONNAME = 'Saint Petersburg'
      and date(date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE )) = date(STR_TO_DATE('26.06.2018','%d.%m.%Y'));