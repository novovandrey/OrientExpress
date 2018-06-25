$(function() {
    $('#tableResult').hide(1);
    $( "#getTrains" ).click(function( ) {
        $('#tableResult').hide(1);
        if(document.querySelector('input[name="traincode"]').validity.valid)
        {
            var datareq = $("#trains :input").serialize();
            $.ajax({
                type: "GET",
                url: "http://localhost:8081/getAllPassengers",
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

});