package org.example;
import io.etcd.jetcd.*;
import io.etcd.jetcd.kv.GetResponse;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        try (Client client = Client.builder().endpoints("http://127.0.0.1:2379").build()) {
            KV kvclient = client.getKVClient();
            ByteSequence key = ByteSequence.from("Octavio Msg".getBytes());
            kvclient.put(
                    key,
                    ByteSequence.from("Hello Berkeley".getBytes())).get();
            CompletableFuture<GetResponse> getFuture = kvclient.get(key);
            GetResponse response = getFuture.get();
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}