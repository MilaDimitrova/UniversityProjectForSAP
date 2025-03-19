<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Grab &amp; Go</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    text-align: center;
                    background-color: #E6AF2E;
                    margin: 0;
                    }

                    .navbar {
                    display: flex;
                    align-items: center;
                    justify-content: space-between; /* Разпределя пространството между менюто и логото */
                    background:#E6AF2E;
                    padding: 10px;
                    }

                    .logo {
                    font-size: 24px;
                    font-weight: bold;
                    color: white;
                    margin-right: 20px;
                    }

                    .logo span {
                    color: black;
                    font-weight: bold;
                    }

                    .menu {
                    display: flex;
                    gap: 20px;
                    }

                    .menu a {
                    text-decoration: none;
                    color: black;
                    font-size: 18px;
                    }

                    .header {
                    font-family: 'Original Surfer';
                    font-style: normal;
                    font-weight: 400;
                    font-size: 75px;
                    line-height: 94px;
                    margin: 20px 0;
                    }

                    .restaurant-container {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }

                    .restaurant {
                    border: 1px solid rgb(242, 217, 135);
                    padding: 10px;
                    background: rgb(242, 217, 135);
                    text-align: center;
                    }

                    .restaurant img {
                    width: 416px;
                    height: 245px;
                    object-fit: cover;
                    }

                    .button {
                    background:  rgb(242, 217, 135);
                    border-radius: 25%;
                    border: rgb(242, 217, 135);
                    width: 130px;
                    height: 31px;
                    font-family: 'Original Surfer';
                    font-weight: 400;
                    font-size: 25px;
                    line-height: 31px;
                    cursor: pointer;
                    margin-top: 10px;
                    }

                    .subscription-form {
                    display: flex;
                    justify-content: center;
                    gap: 30px;
                    background: rgb(242, 217, 135);
                    padding: 30px;
                    }

                    input {
                    padding: 10px;
                    margin-right: 10px;
                    border: 1px solid #ffa500;
                    border-radius: 5px;
                    color: black
                    }

                    .buttonForm {
                    padding: 10px 20px;
                    background-color: #ffa500;
                    border: none;
                    border-radius: 5px;
                    color: white;
                    font-weight: bold;
                    cursor: pointer;
                    }

                    .buttonForm:hover {
                    background-color: #ff7700;
                    }


                </style>
            </head>
            <body>
                <!-- Навигационна лента -->
                <div class="navbar">
                    <div class="logo">
                        <xsl:value-of select="restaurants/logo/text"/>
                        <span><xsl:value-of select="restaurants/logo/highlight"/></span>
                    </div>
                    <div class="menu">
                        <xsl:for-each select="restaurants/nav/item">
                            <a href="#"><xsl:value-of select="."/></a>
                        </xsl:for-each>
                    </div>
                </div>

                <!-- Заглавие -->
                <div class="header">
                    <xsl:value-of select="restaurants/title"/>
                </div>

                <!-- Ресторанти -->
                <h2><xsl:value-of select="restaurants/section/heading"/></h2>
                <div class="restaurant-container">
                    <xsl:for-each select="restaurants/section/restaurantsList/restaurant">
                        <div class="restaurant">
                            <img src="{image}" alt="{name}"/>
                            <p><xsl:value-of select="name"/></p>
                        </div>
                    </xsl:for-each>
                </div>

                <!-- Бутон -->
                <button class="button"><xsl:value-of select="restaurants/button"/></button>

                <div class="subscription-form">
                    <h1>
                        <xsl:value-of select="subscriptionForm/headerForm" />
                    </h1>
                    <form>
                        <input type="text" placeholder="{subscriptionForm/input/@placeholder}" />
                        <buttonForm type="submit" class="buttonForm">
                            <xsl:value-of select="subscriptionForm/buttonForm" />
                        </buttonForm>
                    </form>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>


