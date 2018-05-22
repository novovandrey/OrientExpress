$(function() {
    $('#stationResult').hide(1);
    $( "#addstationBtn" ).click(function( ) {
        $('#stationResult').hide(1);
        if(document.querySelector('input[name="stationname"]').validity.valid)
        {
            var datareq = $("#station :input").serialize();
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/addstation",
                data: datareq,
                success:function(data) {
                    $('#stationResult').html( data );
                    $('#stationResult').show("slow","swing");
                },
                error:function(data) {
                    alert("error");
                }
            });
        }
        else
        {
            alert("please fill station name");
        }
    });

    $('#trainResult').hide(1);
    $( "#addtrainBtn" ).click(function( ) {
        $('#trainResult').hide(1);
        if(document.querySelector('input[name="traincode"]').validity.valid
        &&document.querySelector('input[name="seatsnumber"]').validity.valid
        )
        {
            var datareq = $("#train :input").serialize();
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/addtrain",
                data: datareq,
                success:function(data) {
                    $('#trainResult').html( data );
                    $('#trainResult').show("slow","swing");
                },
                error:function(data) {
                    alert("error");
                }
            });
        }
        else
        {
            alert("please fill form");
        }
    });

});

