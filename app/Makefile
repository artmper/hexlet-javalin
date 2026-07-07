check-deps:
	./gradlew dependencyUpdates -Drevision=release

dev:
	./gradlew run

setup:
	gradle wrapper --gradle-version 8.13

clean:
	./gradlew clean

build:
	./gradlew clean build

start: dev

install:
	./gradlew installDist

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

.PHONY: build