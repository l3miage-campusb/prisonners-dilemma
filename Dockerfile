# Étape 1 : Utiliser une image de base Java
FROM eclipse-temurin:17-jdk-alpine

# Dockerfile temporaire pour debug
RUN ls -al target


# Étape 2 : Définir le répertoire de travail
WORKDIR /app

# Étape 3 : Copier le JAR dans l'image Docker
COPY target/prisoner_dilemma_g1_2-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : Exposer le port utilisé par l'application
EXPOSE 8080

# Étape 5 : Démarrer l'application
CMD ["java", "-jar", "app.jar"]