package com.example.e_commerce.ui.createproduct;

import static android.content.ContentValues.TAG;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PostProcessor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.e_commerce.R;
import com.example.e_commerce.activities.MainActivity;
import com.example.e_commerce.databinding.CreateProductFragmentBinding;
import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Image;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.utility.ImgManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateProductFragment extends Fragment {
    private TextInputEditText textdesignation;
    private TextInputEditText textprice;
    private TextInputEditText textquantity;
    private AutoCompleteTextView textCategory;
    private TextInputEditText textdecription;
    private Button addbutton;
    private String select;
    private Handler handler;
    private static Boolean ImgIsSelect = false;
    Image image;
    Product product;
    ProgressDialog progressDialog;

    private CreateProductViewModel mViewModel;
    private CreateProductFragmentBinding binding;

    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<String> itemsId;
    private ArrayAdapter<String> autoCompleteItems;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private StorageReference reference;
    private String myUrl;
    private Uri imagepath;
    private StorageTask storageTask;
    private  Boolean isModify= false;
    ImageView imageView;
    FirebaseStorage store = FirebaseStorage.getInstance();

    public static CreateProductFragment newInstance() {
        return new CreateProductFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.hideBottomBar();
        if (getArguments() != null) {
            product  = (Product) getArguments().getSerializable("product");
            isModify = true;

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        handler = new Handler(Looper.getMainLooper());
        mViewModel = new ViewModelProvider(requireActivity()).get(CreateProductViewModel.class);
        binding = CreateProductFragmentBinding.inflate(inflater, container, false);
        imageView = binding.profileimage;
        View root = binding.getRoot();
        auth=FirebaseAuth.getInstance();
        init(root);
        MainActivity.hideBottomBar();

        makeCategory();
        autoCompleteItems = new ArrayAdapter<String>(getContext(), R.layout.category_item, items);
        binding.autoComplete.setAdapter(autoCompleteItems);
        builSelectCategory();

        if (isModify){
            binding.toobar.setTitle("Modifier Le Produit");
            modifyProduct();
        }


        binding.btninserte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImgIsSelect) deleteImage();
                else ChoseImage();
            }
        });


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initData())imageSave2(v);
            }
        });


        binding.toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getActivity().onBackPressed();
            }
        });

        return root;
    }





    private void deleteImage() {
        binding.profileimage.setImageResource(R.drawable.profil);
        binding.btninserte.setText(R.string.choisir_image);
        binding.txtVImageIn.setText(R.string.selctionner_image);
        ImgIsSelect = false;

    }



    private void ChoseImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        resultLauncher.launch(intent);

    }




    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data.getData() != null) {
                            imagepath = data.getData();
                            binding.profileimage.setImageURI(imagepath);
                            binding.txtVImageIn.setText("Image selectionnée");
                            binding.btninserte.setText("RETIRER IMAGE");
                            ImgIsSelect = true;

                        }
                    }
                }
            });





    public void init(View view) {
        textdesignation = view.findViewById(R.id.name);
        textprice = view.findViewById(R.id.price);
        textquantity = view.findViewById(R.id.quantity);
        textCategory = view.findViewById(R.id.autoComplete);
        textdecription = view.findViewById(R.id.description);
        addbutton = view.findViewById(R.id.btnaddproduct);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }





    private String designation, price, quantity, category, description;

    public Boolean initData() {
        designation = textdesignation.getText().toString().trim();
        price = textprice.getText().toString().trim();
        quantity = textquantity.getText().toString().trim();
        category = textCategory.getText().toString().trim();
        description = textdecription.getText().toString().trim();


        if (TextUtils.isEmpty(designation)) {
            textdesignation.setError("Name required");
            return false;
        }
        if (TextUtils.isEmpty(price)) {
            textprice.setError("Price is required");
            return false;
        }
        if (TextUtils.isEmpty(quantity)) {
            textquantity.setError("Quantity is required");
            return false;
        }
        if (TextUtils.isEmpty(category)) {
            textCategory.setError("select category");
            return false;
        }
        if (TextUtils.isEmpty(description)) {
            textdecription.setError("description is required");
            return false;
        }
        if (!ImgIsSelect && !isModify) {
            binding.txtVImageIn.setError("Image non selectionnée");
            return false;
        }

        return true;
    }




    @SuppressLint("NewApi")
    public void saveProduct(String myUrl) {
        DocumentReference docProduct;
        if (isModify)  docProduct = db.collection(Product.class.getSimpleName()).document(product.getId());
        else  docProduct = db.collection(Product.class.getSimpleName()).document();
        DocumentReference docImage = db.collection(Image.class.getSimpleName()).document();


        image = new Image();
        if (!isModify) {
            product = new Product();

            product.setTimestamp(Date.from(Instant.now()));
            product.setUser_id(auth.getCurrentUser().getUid());
            product.setId(docProduct.getId());

        }
        product.setName(designation);
        product.setPrice(Double.parseDouble(price));
        product.setDescription(description);
        product.setQuantity(Double.parseDouble(quantity));
        product.setCategory_id(itemsId.get(items.indexOf(category)));

        if (imagepath!= null) {
            image.setProductId(docProduct.getId());
            image.setTimestamp(Date.from(Instant.now()));
            image.setUrl(myUrl);


            docImage.set(image).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    product.setImg_id(image.getUrl());
                    loadProduct(docProduct,product);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }else loadProduct(docProduct,product);
    }





    public Boolean  loadProduct(DocumentReference d,Object o){
        return d.set(o).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).isSuccessful();

    }




    public void builSelectCategory() {
        binding.autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
            }
        });
    }




    private void makeCategory() {
        List<Category> categoryList = new ArrayList<>();
        itemsId = new ArrayList<>();
        db.collection(Category.class.getSimpleName())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) return;
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot doc : list) {
                    Category category = doc.toObject(Category.class);
                    items.add(category.getName());
                    itemsId.add(category.getId());
                }
            }
        });
    }




    public String imageSave2(View v) {




        if(imagepath!=null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Ajout de produit");
            progressDialog.setMessage("En cours,Veuillez patientez...");
            progressDialog.create();

            StorageReference storeR = store.getReference();
            StorageReference imageStore = storeR.child("images");
            StorageReference thisImgStore= imageStore.child(
                    System.currentTimeMillis()+"."+getExt(imagepath)
            );
            progressDialog.show();
            thisImgStore.putFile(imagepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoStringLink = uri.toString();
                                    Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                                    saveProduct(uri.toString());
                                    progressDialog.dismiss();
                                    navigateToDetail(v, product);

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Echec de telechargement de l'image", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                }
            });
        }else {
            saveProduct("");
            navigateToDetail(v, product);
        }
        return "";
    }




    private  String getExt(Uri uri1){
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri1));
    }




    public void navigateToDetail(View v,Product product){
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",product);
        NavOptions options = new NavOptions.Builder().setPopUpTo(R.id.createProductFragment, true).build();
        Navigation.findNavController(v).navigate(R.id.action_createProductFragment_to_mon_produit_Fragment,bundle,options);
    }




    public void  modifyProduct(){
        DocumentReference docCategrie = db.collection(Category.class.getSimpleName()).document(product.getCategory_id());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Veuillez patientez");
        progressDialog.create();
        progressDialog.show();
        docCategrie.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Category category =documentSnapshot.toObject(Category.class);
                binding.name.setText(product.getName());
                binding.price.setText(String.valueOf(product.getPrice()));
                binding.quantity.setText(String.valueOf(product.getQuantity()));
                binding.description.setText(product.getDescription());

                binding.autoComplete.setText(autoCompleteItems.getItem(items.indexOf(category.getName())),false);

                ImgManager.loadImage(product.getImg_id(), binding.profileimage, requireActivity());
                binding.txtVImageIn.setText("Image selectionnée");
                binding.btninserte.setText("CHANGER IMAGE");
                binding.btnaddproduct.setText("Modifier");
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                getActivity().onBackPressed();
            }
        });

    }





}