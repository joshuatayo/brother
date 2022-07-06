package com.brother;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.brother.api.ProductAPI;
import com.brother.api.ColourAPI;
import com.brother.api.ModelAPI;
import com.brother.entities.Product;
import com.brother.entities.Colour;
import com.brother.entities.Model;
import com.brother.utilities.APIClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class Brother {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Brother window = new Brother();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Brother() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 526, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BROTHER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(200, 11, 92, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lbl_toner_model = new JLabel("Toner Model");
		lbl_toner_model.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_toner_model.setBounds(57, 88, 170, 38);
		frame.getContentPane().add(lbl_toner_model);
		
		JLabel lbl_toner_colour = new JLabel("Toner Colour");
		lbl_toner_colour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_toner_colour.setBounds(57, 149, 170, 38);
		frame.getContentPane().add(lbl_toner_colour);
		
		JLabel lbl_toner_barcode = new JLabel("Toner Identifier");
		lbl_toner_barcode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_toner_barcode.setBounds(57, 208, 170, 38);
		frame.getContentPane().add(lbl_toner_barcode);
		
		JLabel lbl_product_description = new JLabel("Toner Description");
		lbl_product_description.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_product_description.setBounds(57, 274, 170, 38);
		frame.getContentPane().add(lbl_product_description);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxTonerModel = new JComboBox();
		comboBoxTonerModel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxTonerModel.setBounds(237, 88, 203, 38);
		frame.getContentPane().add(comboBoxTonerModel);
		
		//populating toner model dropdown list with data from models table;
		populateTonerModel(comboBoxTonerModel);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxTonerColour = new JComboBox();
		comboBoxTonerColour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxTonerColour.setBounds(237, 149, 203, 38);
		frame.getContentPane().add(comboBoxTonerColour);
		
		//populating toner colour dropdown list with data from colours table;
		populateTonerColour(comboBoxTonerColour);
		
		JTextPane txtTonerBarcode = new JTextPane();
		txtTonerBarcode.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtTonerBarcode.setBounds(237, 208, 203, 38);
		frame.getContentPane().add(txtTonerBarcode);
		
		
		JTextArea textAreaProductDescription = new JTextArea();
		textAreaProductDescription.setColumns(20);
		textAreaProductDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaProductDescription.setBounds(237, 274, 203, 72);
		frame.getContentPane().add(textAreaProductDescription);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTonerBarcode.setText("");
				textAreaProductDescription.setText("");
				comboBoxTonerModel.setSelectedItem("");
				comboBoxTonerColour.setSelectedItem("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReset.setBounds(237, 373, 89, 38);
		frame.getContentPane().add(btnReset);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//calling save()
				save(txtTonerBarcode, textAreaProductDescription, comboBoxTonerModel,comboBoxTonerColour);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(351, 373, 89, 38);
		frame.getContentPane().add(btnSave);
	}
	
	//get Toner Models
	@SuppressWarnings("rawtypes")
	private void populateTonerModel(JComboBox comboBoxTonerModel) {
		try {
			ModelAPI modelAPI = APIClient.getClient().create(ModelAPI.class);
			modelAPI.getAllModel().enqueue(new Callback<List<Model>>() {
				
				@SuppressWarnings("unchecked")
				@Override
				public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
					// TODO Auto-generated method stub
					if (response.isSuccessful()) {
						comboBoxTonerModel.addItem("");
						for(Model model : response.body()) {
							comboBoxTonerModel.addItem(model.getName());
						}
					}
				}
				
				@Override
				public void onFailure(Call<List<Model>> arg0, Throwable t) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, t.getMessage());
				}
			});
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	//get Toner Colours
	@SuppressWarnings("rawtypes")
	private void populateTonerColour(JComboBox comboBoxTonerColour) {
		try {
			ColourAPI colourAPI = APIClient.getClient().create(ColourAPI.class);
			colourAPI.getAllColour().enqueue(new Callback<List<Colour>>() {
				
				@SuppressWarnings("unchecked")
				@Override
				public void onResponse(Call<List<Colour>> call, Response<List<Colour>> response) {
					// TODO Auto-generated method stub
					if (response.isSuccessful()) {
						comboBoxTonerColour.addItem("");
						for(Colour colour : response.body()) {
							comboBoxTonerColour.addItem(colour.getName());
						}
					}
				}
				
				@Override
				public void onFailure(Call<List<Colour>> arg0, Throwable t) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, t.getMessage());
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	//save Product
	@SuppressWarnings("rawtypes")
	private void save(JTextPane txtTonerBarcode, 
			JTextArea textAreaProductDescription, 
			JComboBox comboBoxTonerModel, 
			JComboBox comboBoxTonerColour) {
		
		String identifier = txtTonerBarcode.getText().toString();
		String description = textAreaProductDescription.getText().toString();
		String tonerModel = comboBoxTonerModel.getSelectedItem().toString();
		String tonerColour = comboBoxTonerColour.getSelectedItem().toString();
		
		if(identifier.isEmpty() || identifier.equals("")) {
		       JOptionPane.showMessageDialog(null,"Please enter a Identifier"); 
		}else if(!isNumeric(identifier)) {
			JOptionPane.showMessageDialog(null,"Identifier should be a number"); 
		}else if(description.isEmpty() || description.equals("")) {
			JOptionPane.showMessageDialog(null,"Please enter product description"); 
		}else if(description.length() > 100) {
			JOptionPane.showMessageDialog(null,"Product description must be between 1 to 100 character"); 
		}else if(tonerModel.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Please select Toner Model"); 
		}else if(tonerColour.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Please select Toner Colour"); 
		}else {
			try {
				Product product = new Product();
				product.setIdentifier(identifier);
				product.setDescription(description);
				product.setModel(tonerModel);
				product.setColour(tonerColour);
				ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
				productAPI.addProduct(product).enqueue(new Callback<Void>() {
					
					@Override
					public void onResponse(Call<Void> arg0, Response<Void> response) {
						if(response.isSuccessful()) {
							JOptionPane.showMessageDialog(null,"Saved Successfully"); 
							txtTonerBarcode.setText("");
							textAreaProductDescription.setText("");
							comboBoxTonerModel.setSelectedItem("");
							comboBoxTonerColour.setSelectedItem("");
							
						} else {
							
//							JsonObject json = new JsonObject();
//							JsonArray jsonArray = json.getAsJsonObject("all").getAsJsonArray("message");
//							if (jsonArray == null || jsonArray.size() == 0) {
//								JOptionPane.showMessageDialog(null, json.toString());
//							     
//							}
//							JOptionPane.showMessageDialog(null, jsonArray);
							JOptionPane.showMessageDialog(null, "Product already exist");
						}
					}
					
					@Override
					public void onFailure(Call<Void> arg0, Throwable t) {
						JOptionPane.showMessageDialog(null, t.getMessage()); 
						
					}
				});
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage()); 
			}
		}
	}
	
	//check is Identifier is Numeric
	@SuppressWarnings("unused")
	public static boolean isNumeric(String string) {
	    int intValue;
	    try {
	        intValue = Integer.parseInt(string);
	        return true;
	    }catch (NumberFormatException e) {
	    	//
	    }
	    return false;
	}
	
	
}
