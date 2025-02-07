package com.example.softlearning.applicationcore.entity.book.appservices;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.softlearning.applicationcore.entity.book.persistence.BookRepository;
import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.mappers.BooksMapper;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializer;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializers;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.SerializersCatalog;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

@Controller
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookRepository bookRepository;
    private Serializer<BooksDTO> serializer;

    // ****** Implementing the business logic methods and common featues (clean code design) ******

    // public Optional<BooksDTO> findByIdent(String ident);

    // public List<BooksDTO> findByTitle(String title);

    // public List<BooksDTO> findByPartialIdent(String title);

    // public Integer countByPartialTitle(String title);

    // public BooksDTO save(BooksDTO book);

    // public void deleteByIdent(String ident);

    // public List<BooksDTO> findAll();

    //Esta parece que está dentor de las demás, como yo hacia con los checks, reusando código
    protected Optional BooksDTO getDTO(String ident) { 
        return BookRepository.findByIdent(ident);
    }


    protected BooksDTO getByIdent(String title) throws ServiceException {
        BooksDTO bdto = this.getDTO("title");

        if ( bdto == null ) {
            throw new ServiceException("book " + "title" + " not found");
        }
        return bdto;
    }
    
    
    protected BooksDTO checkInputData(String book) throws ServiceException {
        try {
            BooksDTO bdto = (BooksDTO) this.serializer.deserialize(book, BooksDTO.class);
            BooksMapper.booksFromDTO(bdto); 
            return bdto;
        } catch (Exception e) {
            throw new ServiceException("error in the input book data: " + e.getMessage());
        }
    }


    protected BooksDTO newBook(String book) throws ServiceException, BuildException {
        BooksDTO bdto = this.checkInputData(book);
        
        if (this.getDTO(bdto.getIdent()) == null) {
            return bookRepository.save(bdto);
        } 
        throw new BuildException ("book " + bdto.getIdent() + " already exists");
    }


    protected BooksDTO updateBook(String book) throws ServiceException {
        try {
            BooksDTO bdto = this.checkInputData(book);
            this.getByIdent(bdto.getIdent());
            return bookRepository.save(bdto);
        } catch (ServiceException e) {
            throw e;
        }
    }



    // ****** Implementing the interface methods ******

    @Override
    public String getByIdToJson(int id) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.JSON_BOOK)
                .serialize(this.getById(id));
    }


    @Override
    public String getByIdToXml(int id) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.XML_BOOK)
                .serialize(this.getById("id"));
    }

    
    @Override
    public String addFromJson(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.JSON_BOOK);
        return serializer.serialize(this.newBook(book));
    }


    @Override
    public String addFromXml(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.XML_BOOK);
        return serializer.serialize(this.newBook(book));
    }

    @Override
    public String updateOneFromJson(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.JSON_BOOK);
        return serializer.serialize(this.updateBook(book));
    }


    @Override
    public String updateOneFromXml(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.XML_BOOK);
        return serializer.serialize(this.updateBook(book));
    }


    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            this.getById(id);
            bookRepository.deleteById(id);
        } catch (ServiceException e) {
            throw e;
        }
    }
}
