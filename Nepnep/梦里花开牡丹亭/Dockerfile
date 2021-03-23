FROM php:7.0-apache

COPY src /var/www/html

RUN  echo "Nep{fa7c0dbd5a0b5bcd2b0060e168647ada}">/flag \
    && echo "hacking!!!!" > /var/www/html/waf.txt \
    && chmod 755 /var/www/html/index.php \
    && chmod 777 /var/www/html/waf.txt \
    && chmod 755 /var/www/html/shell.php \
    && chmod 755 /flag \
    #安装zip拓展
    && apt-get update  \
    && apt-get install -y --no-install-recommends libzip-dev  \
    && rm -r /var/lib/apt/lists/*  \
    && docker-php-ext-install -j$(nproc) zip \
