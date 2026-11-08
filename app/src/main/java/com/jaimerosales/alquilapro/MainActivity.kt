package com.jaimerosales.alquilapro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaimerosales.alquilapro.ui.theme.AlquilaProTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField
import android.content.Intent
import android.net.Uri
import android.content.Context
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AlquilaProTheme {
                AlquilaProApp()
            }
        }
    }
}

@Composable
fun AlquilaProApp() {
    var selectedScreen by remember { mutableStateOf("Dashboard") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedScreen == "Dashboard",
                    onClick = { selectedScreen = "Dashboard" },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Dashboard"
                        )
                    },
                    label = { Text("Dashboard") }
                )

                NavigationBarItem(
                    selected = selectedScreen == "Properties",
                    onClick = { selectedScreen = "Properties" },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Apartment,
                            contentDescription = "Properties"
                        )
                    },
                    label = { Text("Properties") }
                )

                NavigationBarItem(
                    selected = selectedScreen == "Payments",
                    onClick = { selectedScreen = "Payments" },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Payments,
                            contentDescription = "Payments"
                        )
                    },
                    label = { Text("Payments") }
                )
            }
        }
    ) { innerPadding ->

        when (selectedScreen) {
            "Dashboard" -> DashboardScreen(innerPadding)
            "Properties" -> PropertiesScreen(innerPadding)
            "Payments" -> PaymentsScreen(innerPadding)
        }
    }
}

@Composable
fun DashboardScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Text(
            text = "Welcome to AlquilaPro",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Manage your rental properties in one place",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Monthly Income",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "€2,450",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Active Properties",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "3 Properties",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val intent = Intent(context, AboutActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("About AlquilaPro")
        }
    }
}

@Composable
fun PropertiesScreen(innerPadding: PaddingValues) {
    var showAddPropertyForm by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Text(
            text = "My Properties",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { showAddPropertyForm = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Property")
        }

        Spacer(modifier = Modifier.height(16.dp))

        PropertyCard(
            name = "City Centre Apartment",
            location = "Dublin City Centre",
            rent = "€1,200 / month"
        )

        Spacer(modifier = Modifier.height(12.dp))

        PropertyCard(
            name = "Riverside Studio",
            location = "Malahide",
            rent = "€850 / month"
        )

        Spacer(modifier = Modifier.height(12.dp))

        PropertyCard(
            name = "Malahide House",
            location = "Malahide",
            rent = "€1,600 / month"
        )
        if (showAddPropertyForm) {
            var propertyName by remember { mutableStateOf("") }
            Text(
                text = "Add New Property",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = propertyName,
                onValueChange = { propertyName = it },
                label = { Text("Property name") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            var propertyLocation by remember { mutableStateOf("") }
            var propertyRent by remember { mutableStateOf("") }
            OutlinedTextField(
                value = propertyLocation,
                onValueChange = { propertyLocation = it },
                label = { Text("Location") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = propertyRent,
                onValueChange = { propertyRent = it },
                label = { Text("Monthly rent") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { showAddPropertyForm = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Save Property"
                )
            }
        }
    }
}

@Composable
fun PropertyCard(
    name: String,
    location: String,
    rent: String
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = location,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = rent,
                style = MaterialTheme.typography.titleMedium
            )

            TextButton(
                onClick = {
                    val uri = Uri.parse("geo:0,0?q=${Uri.encode(location)}")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }
            ) {
                Text("View Location")
            }
        }
    }
}
@Composable
fun PaymentsScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Text(
            text = "Payments",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentCard(
            tenant = "John Murphy",
            property = "City Centre Apartment",
            amount = "€1,200",
            status = "Paid"
        )

        Spacer(modifier = Modifier.height(12.dp))

        PaymentCard(
            tenant = "Sarah Kelly",
            property = "Riverside Studio",
            amount = "€850",
            status = "Paid"
        )

        Spacer(modifier = Modifier.height(12.dp))

        PaymentCard(
            tenant = "Michael Byrne",
            property = "Malahide House",
            amount = "€1,600",
            status = "Pending"
        )
    }
}

@Composable
fun PaymentCard(
    tenant: String,
    property: String,
    amount: String,
    status: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = tenant,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = property,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = amount,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}