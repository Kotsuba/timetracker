(function (global) {
    System.config({
        paths: {
            // псевдоним для пути к модулям
            'npm:': 'node_modules/'
        },
        // указываем загрузчику System, где искать модули
        map: {
            // наше приложение будет находиться в папке app
            app: 'app',
            // пакеты angular
            '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
            '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
            '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
            '@angular/platform-browser': 'npm:@angular/platform-browser',
            '@angular/animations': 'npm:@angular/animations/bundles/animations.umd.js',
            '@angular/animations/browser': 'npm:@angular/animations/bundles/animations-browser.umd.js',
            '@angular/platform-browser/animations': 'npm:@angular/platform-browser/bundles/platform-browser-animations.umd.js',
            '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
            '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
            '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
            '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',
            '@angular/material': 'npm:@angular/material/bundles/material.umd.js',
            'rxjs': 'npm:rxjs',
            'angular-in-memory-web-api': 'npm:angular-in-memory-web-api/bundles/in-memory-web-api.umd.js',
            '@ngui/datetime-picker': 'npm:/@ngui/datetime-picker/dist/datetime-picker.umd.js',
            'ng2-charts': 'npm:ng2-charts/bundles/ng2-charts.umd.js',
            'angular2-notifications': 'npm:angular2-notifications',
            '@ngx-translate/core': 'node_modules/@ngx-translate/core/bundles',
            '@ngx-translate/http-loader': 'node_modules/@ngx-translate/http-loader/bundles',
            'angular2-cookie':'npm:angular2-cookie',
            'angular2-jwt':'npm:angular2-jwt/angular2-jwt.js'
        },
        // пакеты, которые указывают загрузчику System, как загружать файлы без имени и расширения
        packages: {
            '@angular/platform-browser': {
                main: 'bundles/platform-browser.umd.js'
            },
            'angular2-notifications': {
                main: './dist/index.js',
                defaultExtension: 'js'
            },
            '@ngx-translate/core': {
                main: 'core.umd.js',
                defaultExtension: 'js'
            },
            '@ngx-translate/http-loader': {
                main: 'http-loader.umd.js',
                defaultExtension: 'js'
            },
            'angular2-cookie': {
                main: './core.js',
                defaultExtension: 'js'
            },
            app: {
                main: './main.js',
                defaultExtension: 'js'
            },
            rxjs: {
                defaultExtension: 'js'
            }
        }
    });
})(this);