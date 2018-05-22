$(function() {
    M.updateTextFields();
    var datepickerinctsnce = $('.datepicker').datepicker({ format: 'dd.mm.yyyy' }, { showClearBtn: true });

    $('#tableResult').hide(1);
    $( "#getScheduleByStation" ).click(function( ) {
        $('#tableResult').hide(1);
        if(document.querySelector('input[name="fromSt"]').validity.valid&&
            document.querySelector('input[name="arrivaldate"]').validity.valid)
        {
            var datareq = $("#schedule :input").serialize();
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/stationscheduleData",
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
    $("#myTable").tablesorter();
});