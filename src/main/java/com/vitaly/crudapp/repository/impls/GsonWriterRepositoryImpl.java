package com.vitaly.crudapp.repository.impls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.model.pojo.Writer;
import com.vitaly.crudapp.repository.WriterRepository;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GsonWriterRepositoryImpl implements WriterRepository {

    private static final Gson GSON = new Gson();
    private static final String FILE_PATH = "src/main/resources/writers.json";


    private List<Writer> writersDataSet() {
        try (BufferedReader postsReader = new BufferedReader(new FileReader(FILE_PATH))) {
            return new Gson().fromJson(postsReader, new TypeToken<List<Writer>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("....");
            return Collections.emptyList();
        }
    }

    private void saveChangesWriters(List<Writer> writers) {
        try (BufferedWriter writerWriter = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            GSON.toJson(writers, writerWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer generateId(List<Writer> writers) {
        return writers.stream()
                .mapToInt(Writer::getId).max().orElse(0) + 1;
    }


    @Override
    public Writer getById(Integer id) {
        return writersDataSet().stream().filter(w -> w.getId().equals(id)).findFirst().orElseThrow(() -> new FileSystemNotFoundException("Writer not found"));
    }

    @Override
    public List<Writer> getAll() {
        return writersDataSet().stream().filter(w -> w.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Writer save(Writer writerToCreate) {
        List<Writer> sourcePosts = writersDataSet();
        writerToCreate.setId(generateId(sourcePosts));
        sourcePosts.add(writerToCreate);
        saveChangesWriters(sourcePosts);
        return writerToCreate;
    }


    @Override
    public Writer update(Writer writerToUpdate) {
        List<Writer> writers = writersDataSet()
                .stream().map(existingWriter -> {
                    if (existingWriter.getId().equals(writerToUpdate.getId())) {
                        return writerToUpdate;
                    }
                    return existingWriter;
                }).collect(Collectors.toList());
        saveChangesWriters(writers);
        return writerToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writers = writersDataSet()
                .stream().peek(existingWriter -> {
                    if (existingWriter.getId().equals(id)) {
                        existingWriter.setStatus(Status.DELETED);
                    }
                }).collect(Collectors.toList());
        saveChangesWriters(writers);
    }





}
