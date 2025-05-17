package com.core.entities.functional.books;

import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.mappers.BooksMapper;
import com.example.softlearning.applicationcore.entity.book.model.Books;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

public class BooksTest {
    public static void main(String[] args) throws Exception, BuildException, ServiceException {
        //ejemplo de serializacion
        
        Books b;
        b = Books.getInstance(
            "1234",
            10.0,
            false,
            0.0,
            "titulo tal",
            "true",
            "30-04-2025",
            "willingli",
            "978-3-16-148410-0",
            "cover1",
            10,
            "genre",
            "editorial",
            3.0,
            3.0,
            3.0,
            false,
            3.0
        );
        System.out.println(b.getDetails());

        //Mapper
        BooksDTO bdto = BooksMapper.dtoFromBooks(b);
        System.out.println("Autor: " + bdto.getAuthor());

        Books bcopy = BooksMapper.booksFromDTO(bdto);
        System.out.println("Mapper Book: " + bcopy.getDetails());
    }
    
}
/* 
 * 
*/
// package com.core.entities.books;


// public class BooksVolumeTest {

//     @Test
//     public void testVolume() throws BuildException, ServiceException {
//         // Given: a Books instance with known dimensions
//         double height = 10.0;
//         double width = 5.0;
//         double length = 2.0;
        
//         Books book = Books.getInstance(
//             "id123",
//             20.0,
//             false,
//             0.0,
//             "Sample Title",
//             "credit",
//             "01-01-2021",
//             "Author Name",
//             "1234567890123",
//             "hardcover",
//             150,
//             "genre",
//             "editorial",
//             3.0,       // weight
//             height,    // height
//             width,     // width
//             false,
//             length     // length
//         );
        
//         // When: calculating volume
//         double expectedVolume = height * width * length; // 10 * 5 * 2 = 100.0

//         // Then: volume() should return the product of dimensions
//         assertEquals(expectedVolume, book.volume(), "Volume should equal height * width * length");
//     }
// }
