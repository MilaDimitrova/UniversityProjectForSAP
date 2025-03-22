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

                </style>

                <body>
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
                </body>
            </head>
        </html>
    </xsl:template>
</xsl:stylesheet>