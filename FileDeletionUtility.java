import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 */

/**
 * @author Snigdhadeb
 *
 */
public class FileDeletionUtility {

	/**
	 * The method accepts the first argument as the directory location to which the file needs to be deleted, i.e all the file under this directory will be deleted
	 * the second argument will consider the number of days before which the files will be deleted, i.e if the noOfDays = 90 then all the files in that directory 
	 * whose last modified date is before current_date - 90 days will be deleted. 
	 * @author Snigdhadeb
	 * @param directoryLocation
	 * @param noOfDays
	 */
	public void deleteFilesFromDirectory(String directoryLocation, int noOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-noOfDays);
		File directory = new File(directoryLocation);
		if(directory.isDirectory()) {
			File[] listFiles = directory.listFiles();
			if(listFiles.length > 0) {
				for (File file : listFiles) {
					if(file.isFile()) {
						Date lastModifiedDate = new Date(file.lastModified());
						if(lastModifiedDate.before(cal.getTime()))
						{
							System.out.println("File : "+file.getName()+" having created date : "+new Date(file.lastModified()));
							boolean isFileDeleted = file.delete();
							if(isFileDeleted) {
								System.out.println("is deleted successfully!!!");
							}
						}
					}else {
						deleteFilesFromDirectory(file.getPath(), noOfDays);
					}					
				}
			}
		}
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileDeletionUtility utility = new FileDeletionUtility();
		utility.deleteFilesFromDirectory("C:\\Users\\**\\Desktop\\Test", 2);
	}
}
