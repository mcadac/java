package hotfix;

import static java.lang.String.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class LogParser {

	private static final String FILE_NAME_REGEX = "logEvent-**-**";

	private static List<String> lines = new ArrayList<>();

	private static Map<Integer, JSONObject> responseLogs = new HashMap<>();

	private static final Map<Integer, String> ordersWithAttemptToFilter = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String ordersToFilter = "/**/**";
		String logs = "/**/**";

		Collection<File> logsFiles = FileUtils.listFiles(
				new File(logs),
				new RegexFileFilter(".*" + FILE_NAME_REGEX + ".*"),
				DirectoryFileFilter.DIRECTORY);

		try (FileReader outputReader = new FileReader(ordersToFilter)) {
			Iterable<CSVRecord> output = CSVFormat.EXCEL.withDelimiter(',').withFirstRecordAsHeader().parse(outputReader);
			output.forEach(record -> {
				ordersWithAttemptToFilter.put(Integer.parseInt(record.get("orderId")), record.get("attemptId"));
			});
		}
		readFile(logsFiles, ordersWithAttemptToFilter);
	}


	private static void readFile(final Collection<File> logsFiles,
								 final Map<Integer, String> ordersToFilter) throws FileNotFoundException {

		final AtomicInteger quantityApproved = new AtomicInteger(0);
		final StringBuilder transactionsApproved = new StringBuilder();

		final StringBuilder transactionsDeclined = new StringBuilder();
		final AtomicInteger quantityDeclined = new AtomicInteger(0);

		logsFiles.forEach(file -> {
			try (Scanner myReader = new Scanner(file)) {
				while (myReader.hasNextLine()) {

					String data = myReader.nextLine();
					if (isReponseMessage(data)) {
						ordersToFilter.keySet()
								.stream()
								.filter(orderId -> data.contains(orderId.toString()))
								.findFirst()
								.ifPresent(orderId -> {
									JSONObject response = new JSONObject(data.substring(data.indexOf('{'), data.length()));
									String networkMessage = getNetworkMessage(response);
									if (isApproved(networkMessage)) {
										quantityApproved.incrementAndGet();
										transactionsApproved
												.append(orderId).append(" = ")
												.append(networkMessage).append("\n");
									} else {
										quantityDeclined.incrementAndGet();
										transactionsDeclined.append(orderId).append("\n");
									}
								});
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});

		System.out.println("--------- APPROVED ------------");
		System.out.println(transactionsApproved.toString());

		System.out.println("--------- DECLINED ------------");
		System.out.println(transactionsDeclined.toString());

		System.out.println("--------- RESULTS ------------");
		System.out.println(format("Log files processed: %s", logsFiles.size()));
		System.out.println(format("Looking for %s transaction", ordersToFilter.size()));
		System.out.println(format("Transactions approved: %s", quantityApproved.get()));
		System.out.println(format("Transactions declined: %s", quantityDeclined.get()));
		System.out.println(format("Transactions not found: %s", ordersToFilter.size() - (quantityApproved.get() + quantityDeclined.get())));
	}

	private static String getNetworkMessage(JSONObject response) {
		return (String) ((JSONObject) response.get("data")).get("Information message");
	}

	private static boolean isReponseMessage(String data) {
		return data.contains("TRANSACTION_NETWORK_RESPONSE_MESSAGE");
	}

	private static boolean isApproved(String networkMessage) {
		return networkMessage.contains("APPROVED");
	}

}
