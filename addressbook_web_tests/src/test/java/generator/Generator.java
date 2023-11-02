package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                        .addObject(generator)
                        .build()
                        .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
           throw new IllegalArgumentException("Неизветсный тип данных" + type);
        }
    }

    private Object generateData(Supplier<Object> dataSupplier) {
       return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateGroups() {
        return generateData(() -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString( 10))
                .withFooter(CommonFunctions.randomString(10)));
    }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                .withFirstname(CommonFunctions.randomString( 10))
                .withLastname(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString( 10))
                .withMobile(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10)));
        // .withPhoto(randomFile("src/test/resources/images/"))); )
//        var result = new ArrayList<ContactData>();
//         for (int i = 0; i < 5; i++) {
//            result.add(new ContactData()
//                    .withFirstname(CommonFunctions.randomString(i * 10))
//                    .withLastname(CommonFunctions.randomString(i * 10))
//                    .withAddress(CommonFunctions.randomString(i * 10))
//                    .withMobile(CommonFunctions.randomString(i * 10))
//                    .withEmail(CommonFunctions.randomString(i * 10)));
//                   // .withPhoto(randomFile("src/test/resources/images/")));
//        }
//        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
          var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }

    }
}
