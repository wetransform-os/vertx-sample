package io.vertx.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Very simple books "database".
 */
public class BooksDB {

  private JsonArray books;
  private Vertx vertx;

  public BooksDB(Vertx vertx) throws IOException {
    super();

    this.vertx = vertx;
    books = new JsonArray(IOUtils.toString(getClass().getResourceAsStream("books.json"), "UTF-8"));
  }

  protected <T> void runTask(Handler<Future<T>> action, Handler<AsyncResult<T>> handler) {
    Future<T> future = Future.future();
    future.setHandler(handler);
    vertx.runOnContext(x -> {
      action.handle(future);
    });
  }

  @SuppressWarnings("unchecked")
  public void getBooks(Handler<AsyncResult<JsonArray>> handler) {
    runTask(future -> {
      JsonArray array = new JsonArray(new ArrayList<>(books.getList()));
      future.complete(array);
    }, handler);
  }

  public void addBook(JsonObject book, Handler<AsyncResult<Void>> handler) {
    runTask(future -> {
      books.add(book);
      future.complete();
    }, handler);
  }

  public void getBook(String isbn, Handler<AsyncResult<JsonObject>> handler) {
    runTask(future -> {
      Optional<JsonObject> book = books.stream()
          .filter(item -> {
            return item instanceof JsonObject && isbn.equals(((JsonObject) item).getString("isbn"));
          })
          .map(item -> (JsonObject) item)
          .findAny();
      future.complete(book.get());
    }, handler);
  }

}
