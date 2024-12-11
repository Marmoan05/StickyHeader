package com.example.stickyheader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Contact(val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaListaContactos()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaListaContactos() {
    val contactos = remember {
        listOf(
            Contact("Alicia"),
            Contact("Ana"),
            Contact("Bob"),
            Contact("Carlos"),
            Contact("David"),
            Contact("Eustaquio"),
            Contact("Fulano"),
            Contact("Roberto"),
            Contact("Martin"),
            Contact("Maria"),
            Contact("Anastacio"),
            Contact("Morgana"),
            Contact("Bliztcrank"),
            Contact("Mipha"),
            Contact("Juan"),
            Contact("Jose"),
            Contact("Sidon"),
            Contact("Zoe")
        ).sortedBy { it.name }
    }

    val contactosAgrupados = contactos.groupBy { it.name.first() }

    val estadoLista = rememberLazyListState()

    LazyColumn(state = estadoLista, modifier = Modifier.padding(16.dp)) {
        contactosAgrupados.forEach { (inicial, contactos) ->
            stickyHeader {
                Text(
                    text = inicial.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(contactos) { contacto ->
                Text(
                    text = contacto.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp).padding(start = 16.dp)
                )
            }
        }
    }
}
