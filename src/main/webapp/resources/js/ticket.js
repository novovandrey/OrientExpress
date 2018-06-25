$(function() {

    $('#leftColumn').css({height:$('#rightColumn').height()});

    M.updateTextFields();
    var datepickerinctsnce = $('.datepicker').datepicker({ format: 'dd.mm.yyyy' }, { showClearBtn: true });

    $("#button").click( function() {
        $( ".datepicker-done" ).click();
        alert("f");
    });
    //
    // $('.datepicker').on('change', function(){
    //     $(this).next().find('.datepicker-done').click();
    // });

        $(document).on('click', '#Pay', function() {

        if(document.querySelector('input[name="FamilyName"]').validity.valid&&
            document.querySelector('input[name="FirstName"]').validity.valid &&
            document.querySelector('input[name="BirthDate"]').validity.valid)
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