# Étape 1 : Utiliser une image de base Java
FROM eclipse-temurin:17-jdk-alpine


# Étape 4 : Exposer le port utilisé par l'application
EXPOSE 8080

# Étape 5 : Démarrer l'application
CMD ["java", "-jar", "target/prisoner_dilemma_g1_2-0.0.1-SNAPSHOT.jar"]