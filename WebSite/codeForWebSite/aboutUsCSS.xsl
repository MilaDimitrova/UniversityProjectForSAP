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

                    .logoImage{
                    position: absolute;
                    width: 416px;
                    height: 383px;
                    left: 104px;
                    top: 390px;
                    }

                    .logoTitle {
                    position: absolute;
                    width: 400px;
                    left: 582px;
                    top: 415px;
                    font-family: 'Original Surfer';
                    font-style: normal;
                    font-weight: 400;
                    font-size: 65px;
                    line-height: 81px;
                    color: black;
                    }

                    .logoText{
                    position: absolute;
                    width: 689px;
                    height: 217px;
                    left: 616px;
                    top: 526px;
                    text-align: justify;
                    font-family: 'Original Surfer';
                    font-size: 25px;
                    line-height: 31px;
                    }

                    .container-first {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }

                    .container-second {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }

                    .container-third {
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    background: rgb(242, 217, 135);
                    padding: 20px;
                    }

                    .stats{
                    border: 1px solid rgb(242, 217, 135);
                    padding: 10px;
                    background: rgb(242, 217, 135);
                    text-align: center;
                    }

                </style>

                <body>

                    <!-- navigation-->
                    <div class="navbar">
                        <div class="logo">
                            <xsl:value-of select="aboutus/logo/text"/>
                            <span><xsl:value-of select="aboutus/logo/highlight"/></span>
                        </div>
                        <div class="menu">
                            <xsl:for-each select="aboutus/nav/item">
                                <a href="{@link}"><xsl:value-of select="."/></a>
                            </xsl:for-each>
                        </div>
                    </div>

                    <div class="header">
                        <xsl:value-of select="aboutus/title"/>
                    </div>

                    <!-- logo information -->
                    <div class="logoImage">
                        <img src="logo.png" alt="{name}"/>
                    </div>
                    <div class="logoTitle">
                        <xsl:value-of select="aboutus/logoImage/title"/>
                    </div>
                    <div class="logoText">
                        <xsl:value-of select="aboutus/logoImage/text"/>
                    </div>


                    <!-- section for statistics-->
                    <div class="container-first" select="aboutus/Stats/allStats">
                            <div class="stats">
                                <img src="salat.png" alt="{name}"/>
                                <p><xsl:value-of select="name"/>23K</p>
                                <p><xsl:value-of select="name"/>Food sales</p>
                            </div>
                            <div class="stats">
                                <img src="airplane.png" alt="{name}"/>
                                <p><xsl:value-of select="name"/>10K</p>
                                <p><xsl:value-of select="name"/>Delivered food</p>
                            </div>
                            <div class="stats">
                                <img src="plate.png" alt="{name}"/>
                                <p><xsl:value-of select="name"/>2K</p>
                                <p><xsl:value-of select="name"/>Happy customers</p>
                             </div>
                    </div>

                </body>
            </head>
        </html>
    </xsl:template>
</xsl:stylesheet>




