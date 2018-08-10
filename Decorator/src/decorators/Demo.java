package decorators;

public class Demo {

	public static void main(String[] args) {
		String record = "Name, Salary\nSon, 100\nTuan, 400";
		DataSourceDecorator encoded = new CompressionDecorator(
										new EncryptionDecorator(
												new FileDataSource("output.txt")));
		encoded.writeData(record);
		FileDataSource plain = new FileDataSource("output.txt");
		
		System.out.println("Input-------------------");
		System.out.println(record);
		System.out.println("Encoded---------------------");
		System.out.println(plain.readData());
		System.out.println("Decoded---------------------");
		System.out.println(encoded.readData());
	}
}
