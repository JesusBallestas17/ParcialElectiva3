@echo off
echo ====================================
echo  Taller Parcial - Spring Boot JPA
echo ====================================
echo.

echo Verificando Maven...
call mvn -version
if %ERRORLEVEL% neq 0 (
    echo Error: Maven no está instalado o no está en el PATH
    pause
    exit /b 1
)

echo.
echo Compilando el proyecto...
call mvn clean install -DskipTests
if %ERRORLEVEL% neq 0 (
    echo Error en la compilación
    pause
    exit /b 1
)

echo.
echo Ejecutando la aplicación...
echo.
echo La aplicación estará disponible en: http://localhost:8080
echo.
echo Endpoints públicos:
echo   GET  /api/productos/listar?stockMaximo=20
echo   GET  /api/comentarios/listar?fechaDesde=2024-01-01
echo   POST /api/auth/signin
echo   POST /api/auth/signup
echo.
echo Usuario de prueba:
echo   Email: juan.perez@email.com
echo   Password: password123
echo.
echo Para detener la aplicación presione Ctrl+C
echo.

call mvn spring-boot:run