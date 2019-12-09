jQuery(function ($) {

    $('#register-form').submit(function (e) {
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
                } else {
                    $('#error-register').removeAttr('hidden');
                    $('#error-message-register').html(Object.values(json)[0]);
                }
            },
            error: function (request, status, error) {
                alert('<fmt:messagekey="ajax.error"/>');
            }
        });
    });
});
 
