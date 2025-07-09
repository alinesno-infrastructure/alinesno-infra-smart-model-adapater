# Document

In alinesno-smart-model-adapter, `Document` is a document object with vector data. It is defined as follows:

```java
public class Document extends VectorData {

    /**
     * Document ID
     */
    private Object id;

    /**
     * Document content
     */
    private String content;
}
```


- id: Document id
- content: Document content

Since it contains vector data, it can be stored in a VectorStore.

In the `Document` module, besides the  `Document` itself, the following components are also provided:

- **DocumentLoader**: A document loader used to load (read) document content from different sources (such as local disk, database, website, etc.).
- **DocumentParser**: A document parser used to parse different types of documents to ultimately obtain  `Document` objects, such as parsing Word, PDF, HTML, etc.
- **DocumentSplitter**: A document splitter used to split large documents into multiple smaller documents, facilitating Embedding computation and storage in vector databases.

##  DocumentLoader

In alinesno-smart-model-adapter, the following two types of document loaders are provided (with more types to be added in the future):

- **FileDocumentLoader**: A file document loader
- **HttpDocumentLoader**: An HTTP document loader

In the future, we will add more types of document loaders, such as database loaders and FTP loaders, as well as loaders for specific domains, like WeChat public account loaders. Users can also implement their own loaders, and we welcome everyone to participate and share.

## DocumentParser

Document parsers are used to parse different types of documents to ultimately obtain `Document` objects. alinesno-smart-model-adapter has built-in document parsers as follows:

- **PdfBoxDocumentParser**: Parses PDF documents
- **PoiDocumentParser**: Parses Word documents

## DocumentSplitter

Document splitters are used to split large documents into multiple smaller documents, with different splitters suitable for different splitting scenarios. Currently, alinesno-smart-model-adapter provides the following document splitters:

- **SimpleDocumentSplitter**: Splits documents using regular expressions
- **MarkdownDocumentSplitter**: Splits Markdown content
- **ParagraphDocumentSplitter**: Splits documents by paragraphs
