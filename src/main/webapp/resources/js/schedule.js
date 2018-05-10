$(function() {
    $('#tableResult').hide(1);
    $('#tableResultDetail').hide(1);
    $( "#getSchedule" ).click(function( ) {
        $('#tableResult').hide(1);
        $('#tableResultDetail').hide(1);
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
        var trainData = {};
        trainData.traincode =  $(this).find('.traincode').text();
        trainData.departuredate = $(this).find('.departuredate').text();
        trainData.fromSt =  $('input[name="fromSt"]').val();
        trainData.toSt = $('input[name="toSt"]').val();

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/scheduleDetail",
            data: trainData,
            success:function(data) {
                $('#tableResultDetail').html( data );
                $('#tableResultDetail').show("slow","swing");
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
});