# Étape 1 : Utiliser une image de base Java
FROM eclipse-temurin:17-jdk-alpine

# Étape 2 : Définir le répertoire de travail
WORKDIR /app


# Dockerfile temporaire pour debug
RUN ls -al /app

# Copier le JAR dans le conteneur sous le nom attendu par Railway
COPY target/prisoner_dilemma_g1_2-0.0.1-SNAPSHOT.jar target/prisoner_dilemma_g1_2-0.0.1-SNAPSHOT.jar

# Dockerfile temporaire pour debug
RUN ls -al /app

RUN chmod +x target/prisoner_dilemma_g1_2-0.0.1-SNAPSHOT.jar

# Dockerfile temporaire pour debug
RUN ls -al /app

# Étape 4 : Exposer le port utilisé par l'application
EXPOSE 8080

# Démarrer l'application en utilisant le nom exact du fichier
CMD ["java", "-jar", "target/prisoner_dilemma_g1_2-0.0.1-SNAPSHOT.jar"]