//Directory.java
//Author: Luke Selbeck, Greg Kitzmiller 
//Date: 3/18/2015
//
//Description:
//Start of the file system; root

public class Directory {
	private static int maxChars = 30; // max characters of each file name

	// Directory entries
	private int fsize[];        // each element stores a different file size.
	private char fnames[][];    // each element stores a different file name.

	public Directory( int maxInumber )
         { // directory constructor
		fsizes = new int[maxInumber];     // maxInumber = max files
		for ( int i = 0; i < maxInumber; i++ ) 
		 fsize[i] = 0;                 // all file size initialized to 0
		fnames = new char[maxInumber][maxChars];
		String root = "/";                // entry(inode) 0 is "/"
		fsize[0] = root.length( );        // fsize[0] is the size of "/".
		root.getChars( 0, fsizes[0], fnames[0], 0 ); // fnames[0] includes "/"
	}

	public int bytes2directory( byte data[] )
        {
		// assumes data[] received directory information from disk
		// initializes the Directory instance with this data[]
                // gets the size of the file from the offset
                // the name of the file getting the characters from the array 
            int offset = 0; 
             for (int i=0; i<fsizes.length; i++, offset += 4) 
                 { 
                  fsizes[i] = SysLib.bytes2int(data, offset); //stores file sizes 
                 }
             for (int i =0; i < fnames.length; i++)
                 {
                   String fname = new String(data, offset, maxChars *2); 
                   fname.getChars(0, fsizes[i], fnames[i], 0);  
                   offset += fsizes[i]; 
                 }
	}

	public byte[] directory2bytes( )
        {
		// converts and return Directory information into a plain byte array
		// this byte array will be written back to disk
		// note: only meaningfull directory information should be converted
		// into bytes.

             byte[] byteDirectory = new byte[(fsizes.length * 4) + (fsizes.length * maxchars * 2)]; 
             int offset = 0; 
            
            for(int i = 0; i < fsizes.length; i++, offset += 4)
               { 
                 SysLib.int2bytes(fsizes[i], byteArray, offset); 
               } 

            for (int i =0; i < fsizes.length; i++)
               { 
                  String fname = new String(fnames[i], 0, fsizes[i]); 
                  toWrite = fname.getBytes(); 

                  for (int j = 0; j < toWrite.length; j++)
                  { 
                    byteDiretory[offset] = (byte) fnames[i][j]; 
                    offset++;
                  } 
               }
             return byteArray; 
	}

	public short ialloc( String filename ) {
		// filename is the one of a file to be created.
		// allocates a new inode number for this filename
	}

	public boolean ifree( short iNumber ) {
		// deallocates this inumber (inode number)
		// the corresponding file will be deleted.
	}

	public short namei( String filename ) {
		// returns the inumber corresponding to this filename
	}
}
