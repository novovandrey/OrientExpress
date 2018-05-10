<%--
  Created by IntelliJ IDEA.
  User: Andrey.Novov
  Date: 04.05.2018
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PayKeeper</title>
    <link rel="stylesheet" type="text/css" href="/resources/paygate_files/cardinfo.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="charset" content="utf-8">

    <script src="/resources/paygate_files/jquery-1.11.3.min.js"></script>
    <script src="/resources/paygate_files/jquery.inputmask.bundle.min.js"></script>
    <script src="/resources/paygate_files/cardinfo.js"></script>
    <style></style>
</head>
<body>
<div class="main_wrap">
    <div class="header">
        <div class="brs-logo"></div>
        <div class="pk-logo"></div>
        <div class="clear"></div>
    </div>
    <div class="content_wrap">
        <div class="title">
            BankCard payment
        </div>
        <div class="shadow"></div>
        <div class="payinfo">
            <div class="row border">
                <label>Site</label>
                <div class="value">http://parovoz.net</div>
                <div class="clear"></div>
            </div>
            <div class="row border">
                <label>Заказ</label>
                <div class="value">Ticket Payment</div>
                <div class="clear"></div>
            </div>
            <div class="row">
                <label>Сумма</label>
                <div class="value"><script>document.write(number_format('${payment}', 2, '.', ' '));</script>${payment} RUB</div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="left-shadow"></div>
        <div class="right-shadow"></div>
        <form method="post" novalidate="" id="card_form" autocomplete="off" action="https://testgw.paykeeper.ru/result.php">
            <input type="hidden" name="callback" value="https://demo.paykeeper.ru/testgw/result/" readonly="readonly">
            <input type="hidden" name="payment_id" value="6916257" readonly="readonly">
            <input type="hidden" name="count" value="2" readonly="readonly">
            <div class="cardinfo">
                <div class="form-validate-error" style="display:none;">
                    Error: please check input data!
                </div>
                <div class="row">
                    <label>Card #</label>
                    <div class="form-group">
                        <input type="tel" data-validate="required,check_card_number" id="number_card" name="cardnr" value="5100 4772 8001 3333" class="valid">
                        <span class="valid-ok"></span>
                        <div class="info">from 12 to 19 digit</div>

                    </div>
                    <div class="clear"></div>

                </div>
                <div class="row addpadding">
                    <label>Validity to</label>
                    <div class="form-group">
                        <table class="select_wrap">
                            <tbody><tr>
                                <td style="vertical-align:top">
                                    <select id="month_card" data-validate="required" data-custom-validate="" name="validMONTH" class="valid">
                                        <option value="">Month</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                    <span class="delimiters">/</span>
                                </td>
                                <td>
                                    <select id="year_card" data-validate="required" name="validYEAR" class="valid">
                                        <option value="">Year</option>
                                        <option value="18">2018</option><option value="19">2019</option><option value="20">2020</option><option value="21">2021</option><option value="22">2022</option><option value="23">2023</option><option value="24">2024</option><option value="25">2025</option><option value="26">2026</option><option value="27">2027</option><option value="28">2028</option></select>
                                    <span class="valid-ok"></span>
                                </td>
                            </tr><tr>
                            </tr></tbody></table>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <label>Card Holder</label>
                    <div class="form-group">
                        <input type="text" id="owner_card" data-validate="required" name="cardname" value="IVAN IVANOV" class="valid">
                        <span class="valid-ok"></span>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <label>Code CVV2</label>
                    <div class="form-group cvv">
                        <input type="tel" id="cvv_card" data-validate="required,check_card_cvv" name="cvc2" value="333" class="valid">
                        <span class="valid-ok"></span>
                        <div class="info">3 digit from card's back side</div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="form-actions">
                <div class="action_wrap">
                    <input type="button" value="Pay" id="submit_card_form">
                </div>
                <div class="action_wrap">
                    <span class="or">or</span>
                    <a href="https://testgw.paykeeper.ru/result.php?callback=https://demo.paykeeper.ru/testgw/result/&amp;payment_id=6916257" class="cancel_pay">Refuse payment</a>
                </div>
                <div class="clear"></div>
            </div>
        </form>
        <div class="add-info">
            This site supports 128-bit encryption. The information entered on this page is protected by special way.
        </div>
        <%--<div class="help-card">--%>
            <%--<div class="about-card">--%>
                <%--<a id="help_card_link">Как правильно ввести информацию с карты</a>--%>
                <%--<div class="help_card_image" id="help_card_image">--%>
                    <%--<img src="/resources/paygate_files/help_card.png" alt="">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
    <div class="footer">
        <div class="paysystem">
            <div class="item">
                <div class="image_wrap">
                    <img src="/resources/paygate_files/visa.png" alt="">
                </div>
                <div class="image_wrap">
                    <img src="/resources/paygate_files/by_visa.png" alt="">
                </div>
            </div>
            <div class="item">
                <div class="image_wrap">
                    <img src="/resources/paygate_files/mastercard.png" alt="">
                </div>
                <div class="image_wrap">
                    <img src="/resources/paygate_files/mastercard_secure.png" alt="">
                </div>
            </div>
            <div class="item">
                <div class="image_wrap">
                    <img src="/resources/paygate_files/american_express.png" alt="">
                </div>
                <div class="image_wrap">
                    <img src="/resources/paygate_files/american_express_safe.png" alt="">
                </div>
            </div>
            <div class="item one_image">
                <div class="image_wrap">
                    <img src="/resources/paygate_files/thawte.png" alt="">
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="add-info">

        </div>
    </div>
</div>


</body></html>
