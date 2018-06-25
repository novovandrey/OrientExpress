$(function() {
    $('select').formSelect();
    M.updateTextFields();
    var datepickerinctsnce = $('.datepicker').datepicker({ format: 'dd.mm.yyyy' }, { showClearBtn: true });

    $( ".datepicker-day-button" ).click(function( ) {
        $( ".datepicker-done" ).click();
    });

    $( "#switchbtn" ).click(function( ) {
        if(document.querySelector('input[name="fromSt"]').validity.valid&&
            document.querySelector('input[name="toSt"]').validity.valid &&
            document.querySelector('input[name="departuredate"]').validity.valid) {
            var swap = $('input[name="fromSt"]').val();
            $('input[name="fromSt"]').val($('input[name="toSt"]').val());
            $('input[name="toSt"]').val(swap);
        }

    })
    $('#tableResult').hide(1);
    $('#tableResultDetail').hide(1);
    $( "#getSchedule" ).click(function( ) {
        $('#tableResult').hide(1);
        $('#tableResultDetail').hide(1);
        if ((document.querySelector('input[name="fromSt"]').validity.valid&&document.querySelector('input[name="toSt"]').validity.valid)&&($( 'input[name="fromSt"]').val()===$( 'input[name="toSt"]').val())){
            alert("Station from and station to is the same")
            return false;
        }
        if(document.querySelector('input[name="fromSt"]').validity.valid&&
            document.querySelector('input[name="toSt"]').validity.valid &&
            document.querySelector('input[name="departuredate"]').validity.valid)
        {
            var datareq = $("#schedule :input").serialize();
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/findSchedule",
                data: datareq,
                success:function(data) {
                    $('#tableResult').html( data );
                    $('#tableResult').show("slow","swing");
                }
            });
        }
        else
        {
            alert("please fill form");
        }
    });

    $(document).on('click', '#tableRow>.resultRow', function() {
        $('#tableRow tr').removeClass("active");

        $(this).addClass("active");
        var trainData = {};
        trainData.traincode =  $(this).find('.traincode').text();
        trainData.departuredate = $(this).find('.departuredate').text();
        trainData.fromSt =  $('input[name="fromSt"]').val();
        trainData.toSt = $('input[name="toSt"]').val();
        //todo
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/scheduleDetail",
            data: trainData,
            success:function(data) {
                $('#tableResultDetail').html( data );
                $('#tableResultDetail').show("slow","swing");
                $('#tableResultDetail').css({height:$('#tableResult').height()});
            }
        });
    });

    $(document).on('click', '#buyTicket', function() {
        var trainData = {};
        trainData.traincode = $('input[name="trcode"]').val();
        trainData.departuredate = $('input[name="depdate"]').val();
        trainData.departurestation = $('input[name="fromSt"]').val() ;
        trainData.arrivalstation = $('input[name="toSt"]').val();
        location.href = "/buyTicket?traincode="+trainData.traincode+"&departuredate="+trainData.departuredate+"&departurestation="+trainData.departurestation+"&arrivalstation="+trainData.arrivalstation;
    });

    $(document).on('click', '#modalShow', function() {

        setTimeout(function() {

            var trainData1 = {};
            trainData1.traincode = $('input[name="trcode"]').val();
            trainData1.departuredate = $('input[name="depdate"]').val();
            trainData1.fromSt = $('input[name="fromSt"]').val() ;
            trainData1.toSt = $('input[name="toSt"]').val();

            $.ajax({
                type: "GET",
                url: "http://localhost:8080/getmarkers",
                data: trainData1,
                success:function(data) {
                    //alert(data);
                    var map = L.map( 'map', {
                        center: [20.0, 5.0],
                        minZoom: 5,
                        zoom: 5
                    })

                    L.tileLayer( 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>',
                        subdomains: ['a', 'b', 'c']
                    }).addTo( map )

                    var myURL = "";

                    var myIcon = L.icon({
                        iconUrl: myURL + '/resources/maps/images/pin24.png',
                        iconRetinaUrl: myURL + '/resources/maps/images/pin48.png',
                        iconSize: [29, 24],
                        iconAnchor: [9, 21],
                        popupAnchor: [0, -14]
                    })

                    var latlngs = Array();

                    for ( var i=0; i < data.length; ++i )
                    {
                        var mark_1 = L.marker( [data[i].lat, data[i].lng], {icon: myIcon} )
                            .bindPopup( data[i].name )
                            .addTo( map );
                        latlngs.push(mark_1.getLatLng());
                    }

                    var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
                    map.fitBounds(polyline.getBounds());
                    map.invalidateSize();
                }

            });


        }, 10);

        // var datareq = $("#schedule :input").serialize();
        // $.ajax({
        //     type: "GET",
        //     url: "http://localhost:8080/findSchedule",
        //     data: datareq,
        //     success:function(data) {
        //         $('#tableResult').html( data );
        //         $('#tableResult').show("slow","swing");
        //     }
        // });

    });


});

