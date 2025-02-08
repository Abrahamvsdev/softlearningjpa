package com.example.softlearning.applicationcore.entity.book.appservices;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.softlearning.applicationcore.entity.book.persistence.BookRepository;
import com.example.softlearning.applicationcore.entity.book.dtos.BooksDTO;
import com.example.softlearning.applicationcore.entity.book.mappers.BooksMapper;
import com.example.softlearning.applicationcore.entity.book.model.Books;
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


    // public Optional<BooksDTO> findByIdent(String ident);

    // public List<BooksDTO> findByTitle(String title);

    // public List<BooksDTO> findByPartialIdent(String title);

    // public Integer countByPartialTitle(String title);

    // public BooksDTO save(BooksDTO book);

    // public void deleteByIdent(String ident);

    // public List<BooksDTO> findAll();

    
    protected BooksDTO getDTO(String ident) { 
        return bookRepository.findByIdent(ident).orElse(null); // El Optional está definido en el "BookRepository", que está instanciado arriba
    }


    protected BooksDTO getByIdent(String ident) throws ServiceException {
        BooksDTO bdto = this.getDTO("ident");

        if ( bdto == null ) {
            throw new ServiceException("book " + ident + " not found");
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

    
    public String getByIdentToJson(String ident) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.JSON_BOOK)
                .serialize(this.getByIdent(ident));
    }


    @Override
    public String getByIdentToXml(String ident) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.XML_BOOK)
                .serialize(this.getByIdent(ident));
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
    public String getByIdentToXml(String ident) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.XML_BOOK).serialize(this.getByIdent(ident));
    }


    @Override
    public void deleteByIdent(String ident) throws ServiceException {
        try {
            this.getByIdent("ident");
            bookRepository.deleteByIdent("ident");
        } catch (ServiceException e) {
            throw e;
        }
    }

    
}
