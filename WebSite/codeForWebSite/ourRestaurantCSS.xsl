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
                    justify-content: space-between;
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

                    .restaurant-container2 {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }

                    .restaurant-container3 {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }

                    .restaurant-container4 {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }


                </style>

                <body>
                    <div class="navbar">
                        <div class="logo">
                            <xsl:value-of select="ourRestaurants/logo/text"/>
                            <span><xsl:value-of select="ourRestaurants/logo/highlight"/></span>
                        </div>
                        <div class="menu">
                            <xsl:for-each select="ourRestaurants/nav/item">
                                <a href="{@link}"><xsl:value-of select="."/></a>
                            </xsl:for-each>
                        </div>
                    </div>

                    <div class="header">
                        <xsl:value-of select="ourRestaurants/title"/>
                    </div>

                    <div class="restaurant-container">
                        <xsl:for-each select="ourRestaurants/section/restaurantsListFirst/restaurant">
                            <div class="restaurant">
                                <img src="{image}" alt="{name}"/>
                                <p><xsl:value-of select="name"/></p>
                            </div>
                        </xsl:for-each>
                    </div>

                    <div class="restaurant-container2">
                        <xsl:for-each select="ourRestaurants/section/restaurantsListSecond/restaurant">
                            <div class="restaurant">
                                <img src="{image}" alt="{name}"/>
                                <p><xsl:value-of select="name"/></p>
                            </div>
                        </xsl:for-each>
                    </div>

                    <div class="restaurant-container3">
                        <xsl:for-each select="ourRestaurants/section/restaurantsListThird/restaurant">
                            <div class="restaurant">
                                <img src="{image}" alt="{name}"/>
                                <p><xsl:value-of select="name"/></p>
                            </div>
                        </xsl:for-each>
                    </div>

                    <div class="restaurant-container4">
                        <xsl:for-each select="ourRestaurants/section/restaurantsListFourth/restaurant">
                            <div class="restaurant">
                                <img src="{image}" alt="{name}"/>
                                <p><xsl:value-of select="name"/></p>
                            </div>
                        </xsl:for-each>
                    </div>
                </body>
            </head>
        </html>
    </xsl:template>
</xsl:stylesheet>