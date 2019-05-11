FROM adoptopenjdk/openjdk11:alpine as build
WORKDIR /workspace/reposicao


COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY ear ear
COPY ejb ejb
COPY web web
# COPY src src

RUN ./mvnw install -DskipTests
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.war)

FROM michelav/wildfly-alpine

WORKDIR /opt/wildfly/standalone/deployments/app-web/

ARG DEPENDENCY=/workspace/reposicao

COPY --from=build ${DEPENDENCY}/ear/target/ear-reposicao-1.0.ear .

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-c", "standalone-full.xml", "-bmanagement", "0.0.0.0"]
