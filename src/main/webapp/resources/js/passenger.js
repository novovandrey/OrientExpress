$(function() {
    $('#tableResult').hide(1);
    $( "#getPassengers" ).click(function( ) {
        $('#tableResult').hide(1);
        if(document.querySelector('input[name="traincode"]').validity.valid&&
            document.querySelector('input[name="arrivaldate"]').validity.valid)
        {
            var datareq = $("#passengers :input").serialize();
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/getAllPassengers",
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