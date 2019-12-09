jQuery(function ($) {

    $('#login-form').submit(function (e) {
        e.preventDefault();
        let frm = $(this);

        $.ajax({
            url: frm.attr('action'),
            type: frm.attr(`method`),
            data: frm.serialize(),
            success: function (response) {
                const stringified = JSON.stringify(response);
                const json = JSON.parse(stringified);
               
                if (json['redirect'] != null) {
                    window.location.href = json['redirect'];
                } else if (json['22']) {
                    $('#error-login').removeAttr('hidden');
                    $('#error-message-login').append('<span><fmt:message key="error.wrong.login"/></span>');
                } else {
                    $('#error-login').removeAttr('hidden');
                    $('#error-message-login').html(Object.values(json)[0]);
                }
            },
            error: function (request, status, error) {
                //alert("<fmt:messagekey="ajax.error"/>");
            }
        });
    });
});
 
