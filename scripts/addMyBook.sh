#!/bin/sh

curl -i -H "Content-Type: application/json" -X POST -d '{"isbn":"123","title":"My Book","author":"Me"}' http://localhost:8080/books
