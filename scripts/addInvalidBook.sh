#!/bin/sh

curl -i -H "Content-Type: application/json" -X POST -d '{"title":"Untitled","author":"Anonymous"}' http://localhost:8080/books
