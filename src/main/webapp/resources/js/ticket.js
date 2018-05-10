$(function() {
        $(document).on('click', '#Pay', function() {

        if(document.querySelector('input[name="FamilyName"]').validity.valid&&
            document.querySelector('input[name="FirstName"]').validity.valid &&
            document.querySelector('input[name="BirthDate"]').validity.valid&&
            document.querySelector('input[name="SeatNumber"]').validity.valid)
        {
            var datareq = $("#ticket :input").serialize();
            var routeinfo = $("#routeInfo :input").serialize();
            datareq +="&"+routeinfo;
            location.href = "/payTicket?"+datareq;
            // $.ajax({
            //     type: "GET",
            //     url: "http://localhost:8080/payTicket",
            //     data: datareq,
            //     success: function(response){
            //         window.location.href = "/paygate.html";
            //     }
            // });
        }
        else
        {
            alert("please fill form");
        }
    });

    //$('#ticketResult').hide(1);

});